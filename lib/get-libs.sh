
#!/bin/sh

#pro mac
wget -P lib/ https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_linux-x64_bin-sdk.zip

unzip lib/openjfx-11.0.2_linux-x64_bin-sdk.zip -d lib/

[ -e lib/openjfx-11.0.2_linux-x64_bin-sdk.zip ] && rm lib/openjfx-11.0.2_linux-x64_bin-sdk.zip
