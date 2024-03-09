# Co se musí nainstalovat
Vzhledem k tomu, že se mi ještě nepodařilo zabalit ho do jar filu, musí se spustit v nějakém IDE (tzn. Intelij, VS code nebo Eclipse) a pak ještě nepoběží bez JDK - [https://javadl.oracle.com/webapps/download/AutoDL?BundleId=249553_4d245f941845490c91360409ecffb3b4](url)
# Jak ho spustit?
Nejdříve se musí spustit gradlew.bat, ten se postará o to, že bude nainstalovaná jak joml (knihovna na Vectory a Matice) tak i lwjgl (light weight java game library, ta má v sobě openGL, openCL, openAL a další knihovny). To se naštěstí musí udělat jen jednou. Potom by už měl jít zapnout přes soubor Launcher.java nacházející se v src/main/java/org/nsgg/main.
