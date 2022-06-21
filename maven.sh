# test if ANDROID_SDK_PLATFORM_DIRECTORY is set and is a valid path and is a directory
if [ -z "$ANDROID_SDK_PLATFORM_DIRECTORY" ] || [ ! -d "$ANDROID_SDK_PLATFORM_DIRECTORY" ]; then
  echo "ANDROID_SDK_PLATFORM_DIRECTORY is not set or is not a valid path"
  echo "exemple :"
  echo "> On Mac :";
  echo "export ANDROID_SDK_PLATFORM_DIRECTORY=/Users/<user>/Library/Android/sdk/platforms"
  echo "> On Ubuntu :";
  echo "export ANDROID_SDK_PLATFORM_DIRECTORY=/home/<user>Android/Sdk/platforms"
  exit 1
fi

# test if ANDROID_SDK_PLATFORM_VERSION is set and is a number and is greater than 24
if [ -z "$ANDROID_SDK_PLATFORM_VERSION" ] || [ ! -n "$ANDROID_SDK_PLATFORM_VERSION" ] || [ "$ANDROID_SDK_PLATFORM_VERSION" -lt 24 ]; then
  echo "ANDROID_SDK_PLATFORM_VERSION is not set or is not a number or is less than 24"
  echo "exemple :"
  echo "export ANDROID_SDK_PLATFORM_VERSION=32"
  exit 1
fi

export ANDROID_JAR_PATH=$ANDROID_SDK_PLATFORM_DIRECTORY/android-$ANDROID_SDK_PLATFORM_VERSION/android.jar

# test if ANDROID_JAR_PATH exist and point to a valid file with jar extension
if [ -f "$ANDROID_JAR_PATH" ] && [ "${ANDROID_JAR_PATH##*.}" = "jar" ]; then
  echo "ANDROID_JAR_PATH is set to $ANDROID_JAR_PATH"
else
  echo "ANDROID_JAR_PATH is not set or is not a valid file"
  exit 1
fi

mvn package
mvn site
