
#!/bin/sh

#pro mac
wget -P lib/ https://download2.gluonhq.com/openjfx/16/openjfx-16_osx-x64_bin-sdk.zip

unzip lib/openjfx-16_osx-x64_bin-sdk.zip -d lib/

[ -e lib/openjfx-16_osx-x64_bin-sdk.zip ] && rm lib/openjfx-16_osx-x64_bin-sdk.zip
