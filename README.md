# Jar file
Je potřeba JDK - [https://javadl.oracle.com/webapps/download/AutoDL?BundleId=249553_4d245f941845490c91360409ecffb3b4](url), potom už je jen potřeba spustit v EngineBuild/libs

**Varování!!!** ke spuštění je potřeba i složka src a věci v ní, pokud budete jar přesouvat nezapomeňte na ni.
# Spuštění v IDE
### Co se musí nainstalovat
JDK - [https://javadl.oracle.com/webapps/download/AutoDL?BundleId=249553_4d245f941845490c91360409ecffb3b4](url)
### Jak ho spustit?
Nejdříve se musí spustit gradlew.bat, ten se postará o to, že bude nainstalovaná jak joml (knihovna na Vectory a Matice) tak i lwjgl (light weight java game library, ta má v sobě openGL, openCL, openAL a další knihovny). To se naštěstí musí udělat jen jednou. Potom by už měl jít zapnout přes soubor Launcher.java nacházející se v src/main/java/org/nsgg/main.

# Stavba jar filu v Intelij
V možnostech gradlu rozbalíte shadowJar a pak kliknete na shadowJar. Pak přesunete složku resources do složky s buildem a dáte ho do složky src/main.
