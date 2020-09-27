#!/usr/bin/env sh
# read .env file and export it
BASEDIR=$(dirname "$0")
set -a
# export defaults
. "${BASEDIR}/.env"
# export overrides if exists
if [ -f "${BASEDIR}/.env.override" ]; then
  . "${BASEDIR}/.env.override"
fi
set +a