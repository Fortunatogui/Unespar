#include <LiquidCrystal.h>
#include <Servo.h>

// LCD: RS, E, D4, D5, D6, D7 nos pinos analógicos A0 a A5
LiquidCrystal lcd(A0, A1, A2, A3, A4, A5);

// Servo da cancela
Servo cancela;
        
// Sensores de vaga (V1 a V4)
const int trigVagas[4] = {4, 6, 8, 11};       // TRIG (verde)
const int echoVagas[4] = {5, 7, 9, 10};       // ECHO (rosa)

// Sensor da entrada (protoboard do meio)
const int trigEntrada = 3;
const int echoEntrada = 2;

// Pino do servo motor
const int pinoServo = 12;

// Distâncias de detecção em cm
const int limiteVaga = 7;
const int limiteEntrada = 7;

bool vagas[4];  // true = livre, false = ocupada

void setup() {
  lcd.begin(16, 2);
  lcd.print("Iniciando...");
  delay(2000);
  lcd.clear();

  // Inicializa a comunicação serial para debug
  Serial.begin(9600);
  Serial.println("Iniciando o sistema...");

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
  cancela.write(0); // Garante que a cancela começa fechada
}

void loop() {
  int vagasLivres = contarVagas();
  mostrarVagas(vagasLivres);
  verificarEntrada(vagasLivres);

  delay(1000); // Pequeno atraso para estabilidade
}

int contarVagas() {
  int vagasLivres = 0;
  Serial.println("\n--- Verificando Vagas ---");
  for (int i = 0; i < 4; i++) {
    long dist = medirDistanciaManual(trigVagas[i], echoVagas[i]);

    Serial.print("Vaga ");
    Serial.print(i + 1);
    Serial.print(": Distancia = ");
    if (dist == -1) {
      Serial.print("Erro de leitura");
    } else {
      Serial.print(dist);
      Serial.print(" cm");
    }

    if (dist != -1 && dist < limiteVaga) {
      vagas[i] = false;  // Vaga ocupada
      Serial.println(" (OCUPADA)");
    } else {
      vagas[i] = true;   // Vaga livre
      vagasLivres++;
      Serial.println(" (LIVRE)");
    }
    delay(100); // Evita interferência entre sensores
  }
  Serial.print("Total de vagas livres: ");
  Serial.println(vagasLivres);
  return vagasLivres;
}

void mostrarVagas(int vagasLivres) {
  lcd.clear();
  if (vagasLivres == 0) {
    lcd.setCursor(0, 0);
    lcd.print("Estac. JGP  Cheio");
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
  Serial.println("\n--- Verificando Entrada ---");
  long entrada = medirDistanciaManual(trigEntrada, echoEntrada);

  Serial.print("Entrada: Distancia = ");
  if (entrada == -1) {
    Serial.println("Erro de leitura");
  } else {
    Serial.print(entrada);
    Serial.println(" cm");
  }

  if (entrada != -1 && entrada < limiteEntrada && vagasLivres > 0) {
    Serial.println("Carro detectado na entrada e ha vagas livres. Abrindo cancela.");
    cancela.write(90);  // Abre a cancela

    // Mantém a cancela aberta enquanto detectar o carro
    int leiturasInvalidas = 0;

while (true) {
  long novaLeitura = medirDistanciaManual(trigEntrada, echoEntrada);

  if (novaLeitura == -1 || novaLeitura > limiteEntrada) {
    leiturasInvalidas++;
  } else {
    leiturasInvalidas = 0; // Reset se uma leitura válida for recebida
  }

  if (leiturasInvalidas >= 5) {
    delay(2000); // Espera um pouco antes de fechar
    break;       // Fecha a cancela
  }

  delay(100);
}


    Serial.println("Fechando cancela.");
    cancela.write(0);   // Fecha a cancela
  } else if (entrada != -1 && entrada < limiteEntrada && vagasLivres == 0) {
    Serial.println("Carro detectado na entrada, mas estacionamento CHEIO.");
  } else {
    Serial.println("Nenhum carro na entrada ou cancela fechada.");
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
    if (micros() - tempoInicio > 30000) return -1; // Timeout
  }
  
    //marca o tempo que pulso(ECHO) chegou
  unsigned long startTime = micros();

  // Espera o ECHO voltar para LOW
  while (digitalRead(echo) == HIGH) {
    if (micros() - startTime > 30000) return -1; // Timeout
  }

  //calcula o tempo que levou ida e volta pra sonar encostar e cehgar no sensor
  long duracao = micros() - startTime;

  //fórmula tempo em distância
  return duracao * 0.034 / 2;
}
