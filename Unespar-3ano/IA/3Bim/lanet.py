# -*- coding: utf-8 -*-

import tensorflow as tf
import matplotlib.pyplot as plt
import numpy as np
import os
from sklearn.metrics import confusion_matrix, ConfusionMatrixDisplay

# 1. Apontar para a sua pasta local com o dataset
# ALTERADO: Removi o download. Você deve fornecer o caminho para a pasta
# que contém as subpastas (COVID, Normal, Viral Pneumonia).
# Exemplo para Windows: 'C:/Users/SeuUsuario/Downloads/COVID-19_Radiography_Database'
# Exemplo para Colab: '/content/COVID-19_Radiography_Database'
base_dir = "C:\\Users\\fortu\\Downloads\\archive"

# 2. Criar os datasets de treino, validação e teste a partir da pasta local
IMG_SIZE = (150, 150)
BATCH_SIZE = 32

# NOVO: Vamos criar todos os três datasets (treino, validação e teste) de uma única fonte.
# Usaremos 70% para treino e 30% para validação/teste.
train_ds = tf.keras.utils.image_dataset_from_directory(
    base_dir,
    validation_split=0.3, # 30% dos dados serão separados para validação e teste
    subset="training",
    seed=123, # A semente (seed) garante que as divisões sejam sempre as mesmas
    image_size=IMG_SIZE,
    batch_size=BATCH_SIZE
)

# NOVO: Criar o conjunto de validação a partir dos 30% restantes
validation_ds = tf.keras.utils.image_dataset_from_directory(
    base_dir,
    validation_split=0.3,
    subset="validation",
    seed=123,
    image_size=IMG_SIZE,
    batch_size=BATCH_SIZE
)

# NOVO: Dividir o conjunto de validação pela metade para criar um conjunto de teste final
val_batches = tf.data.experimental.cardinality(validation_ds)
test_ds = validation_ds.take(val_batches // 2)
validation_ds = validation_ds.skip(val_batches // 2)

class_names = train_ds.class_names
print("Classes encontradas:", class_names)

# 3. Otimizar os datasets para melhor performance (não precisa de alterações)
AUTOTUNE = tf.data.AUTOTUNE
train_ds = train_ds.cache().shuffle(1000).prefetch(buffer_size=AUTOTUNE)
validation_ds = validation_ds.cache().prefetch(buffer_size=AUTOTUNE)
test_ds = test_ds.cache().prefetch(buffer_size=AUTOTUNE)

# 4. Criar o modelo LeNet com base no código de sala de aula
# A arquitetura do modelo é mantida, e a camada de saída se adapta automaticamente.
model = tf.keras.Sequential([
    tf.keras.layers.Rescaling(1./255, input_shape=(150, 150, 3)),
    tf.keras.layers.Conv2D(6, kernel_size=(5, 5), activation='sigmoid', padding='same'),
    tf.keras.layers.MaxPooling2D(pool_size=(2, 2)),
    tf.keras.layers.Conv2D(16, kernel_size=(5, 5), activation='sigmoid'),
    tf.keras.layers.MaxPooling2D(pool_size=(2, 2)),
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(120, activation='sigmoid'),
    tf.keras.layers.Dense(84, activation='sigmoid'),
    # Esta camada se ajusta automaticamente pois `len(class_names)` agora é 3
    tf.keras.layers.Dense(len(class_names), activation='softmax')
])

# A métrica 'accuracy' (acurácia) será registrada durante o treino
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

model.summary()

# 5. Treinar o modelo
# O objeto 'history' vai armazenar as métricas de cada época
epochs = 15
history = model.fit(train_ds, validation_data=validation_ds, epochs=epochs)

# 6. Fazer predições no conjunto de teste para gerar a matriz de confusão
y_true = []
y_pred = []

for images, labels in test_ds:
    preds = model.predict(images, verbose=0)
    y_true.extend(labels.numpy())
    y_pred.extend(np.argmax(preds, axis=1))

# 7. Registrar e exibir a 'matriz de confusão'
# ALTERADO: Título do gráfico atualizado para o novo problema
cm = confusion_matrix(y_true, y_pred)
disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=class_names)
disp.plot(cmap=plt.cm.Blues)
plt.title("Matriz de Confusão - LeNet Raio-X Pulmão")
plt.show()

# 8. Registrar e exibir a 'curva de perda' e a curva de acurácia
acc = history.history['accuracy']
val_acc = history.history['val_accuracy']
loss = history.history['loss']
val_loss = history.history['val_loss']

epochs_range = range(epochs)

plt.figure(figsize=(12, 4))
plt.subplot(1, 2, 1)
plt.plot(epochs_range, acc, label='Acurácia de Treino')
plt.plot(epochs_range, val_acc, label='Acurácia de Validação')
plt.legend(loc='lower right')
plt.title('Acurácia de Treino e Validação')

plt.subplot(1, 2, 2)
plt.plot(epochs_range, loss, label='Perda de Treino')
plt.plot(epochs_range, val_loss, label='Perda de Validação')
plt.legend(loc='upper right')
plt.title('Curva de Perda')
plt.show()