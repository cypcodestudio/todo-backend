#!/usr/bin/env bash
# Run the Todo backend locally.
# Copy .env.example to .env, fill in your Supabase credentials, then run this script.

set -e

if [ -f .env ]; then
  export $(grep -v '^#' .env | xargs)
fi

export JAVA_HOME=/Users/nkululekog/Library/Java/JavaVirtualMachines/corretto-17.0.11/Contents/Home

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx256m"
