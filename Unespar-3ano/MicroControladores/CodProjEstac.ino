#include <LiquidCrystal.h>
#include <Servo.h>

// LCD: RS, E, D4, D5, D6, D7 nos pinos analógicos A0 a A5
LiquidCrystal lcd(A0, A1, A2, A3, A4, A5);

// Servo da cancela
Servo cancela;

// Sensores de vaga (V1 a V4)
const int trigVagas[4] = {5, 7, 9, 11};       // TRIG (verde)
const int echoVagas[4] = {6, 8, 10, 12};      // ECHO (rosa)

// Sensor da entrada (protoboard do meio)
const int trigEntrada = 2;
const int echoEntrada = 4;

// Pino do servo motor
const int pinoServo = 3;

// Distâncias de detecção em cm
const int limiteVaga = 60;
const int limiteEntrada = 60;

bool vagas[4];  // true = livre, false = ocupada

void setup() {
  lcd.begin(16, 2);
  lcd.print("Iniciando...");
  delay(2000);
  lcd.clear();

  // Inicializa pinos dos sensores de vaga
  for (int i = 0; i < 4; i++) {
    pinMode(trigVagas[i], OUTPUT);
    pinMode(echoVagas[i], INPUT);
  }

  // Inicializa sensor de entrada
  pinMode(trigEntrada, OUTPUT);
  pinMode(echoEntrada, INPUT);

  // Inicializa servo
  cancela.attach(pinoServo);
  cancela.write(0);
}

void loop() {
  int vagasLivres = contarVagas();
  mostrarVagas(vagasLivres);
  verificarEntrada(vagasLivres);

  delay(1000);
}

int contarVagas() {
  int vagasLivres = 0;
  for (int i = 0; i < 4; i++) {
    long dist = medirDistanciaManual(trigVagas[i], echoVagas[i]);

    if (dist != -1 && dist < limiteVaga) {
      vagas[i] = false;  // Vaga ocupada
    } else {
      vagas[i] = true;   // Vaga livre
      vagasLivres++;
    }
    delay(100); // Evita interferência entre sensores
  }
  return vagasLivres;
}

void mostrarVagas(int vagasLivres) {
  lcd.clear();
  if (vagasLivres == 0) {
    lcd.setCursor(0, 0);
    lcd.print("Estac. Cheio");
  } else {
    lcd.setCursor(0, 0);
    lcd.print("Livres: ");
    lcd.print(vagasLivres);

    lcd.setCursor(0, 1);
    for (int i = 0; i < 4; i++) {
      if (vagas[i]) {
        lcd.print("V");
        lcd.print(i + 1);
        lcd.print(" ");
      }
    }
  }
}

void verificarEntrada(int vagasLivres) {
  long entrada = medirDistanciaManual(trigEntrada, echoEntrada);

  if (entrada != -1 && entrada < limiteEntrada && vagasLivres > 0) {
    cancela.write(90);  // Abre a cancela

    // Mantém a cancela aberta enquanto detectar o carro
    while (true) {
      long novaLeitura = medirDistanciaManual(trigEntrada, echoEntrada);
      if (novaLeitura == -1 || novaLeitura > limiteEntrada) {
        delay(2000);
        break;  // Carro saiu, fecha cancela
      }
      delay(100);
    }

    cancela.write(0);   // Fecha a cancela
  }
}

long medirDistanciaManual(int trig, int echo) {
  digitalWrite(trig, LOW);
  delayMicroseconds(2);
  digitalWrite(trig, HIGH);
  delayMicroseconds(10);
  digitalWrite(trig, LOW);
  
	//marca o tempo de inicio qu o pulso saiu
  unsigned long tempoInicio = micros();

  // Espera o ECHO ir para HIGH
  while (digitalRead(echo) == LOW) {
    if (micros() - tempoInicio > 30000) return -1;
  }
	
  	//marca o tempo que pulso(ECHO) chegou
  unsigned long startTime = micros();

  // Espera o ECHO voltar para LOW
  while (digitalRead(echo) == HIGH) {
    if (micros() - startTime > 30000) return -1;
  }

  //calcula o tempo que levou ida e volta pra sonar encostar e cehgar no sensor
  long duracao = micros() - startTime;

  //fórmula tempo em distância
  return duracao * 0.034 / 2;
}
