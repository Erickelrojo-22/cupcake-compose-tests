# Práctica de pruebas de Cupcake

Implementación del codelab [Cómo probar la app de Cupcake](https://developer.android.com/codelabs/basic-android-kotlin-compose-test-cupcake?hl=es-419).

Se utiliza el código oficial de la rama `navigation` de [google-developer-training/basic-android-kotlin-compose-training-cupcake](https://github.com/google-developer-training/basic-android-kotlin-compose-training-cupcake/tree/navigation), commit `05af7861946a9bd35961eb8554d0559e3dab45fe`, con su licencia Apache 2.0.

La aplicación conserva el flujo original de cantidad, sabor, fecha de retiro y resumen. Se agregan únicamente las dependencias y las pruebas de interfaz solicitadas, incluidos los ejercicios de cobertura.

## Pruebas

En `app/src/androidTest/java/com/example/cupcake/test`:

- `CupcakeScreenNavigationTest`: 10 pruebas de destino inicial, navegación, botón Arriba y cancelación.
- `CupcakeOrderScreenTest`: 4 pruebas de contenido de inicio, selección y resumen, y habilitación de Next al seleccionar un sabor.
- `ScreenAssertions` y `ComposeRuleExtensions`: funciones auxiliares indicadas por el codelab.

## Ejecución

Abrir esta carpeta en Android Studio, usar JDK 17 y sincronizar Gradle. El proyecto utiliza Android SDK 34 y requiere un dispositivo o emulador con Android 7.0 API 24 o superior.

En Windows:

```powershell
.\gradlew.bat assembleDebug
.\gradlew.bat connectedDebugAndroidTest
```

El informe de las pruebas instrumentadas se genera en `app/build/reports/androidTests/connected/debug/index.html`.
