#!/bin/bash

args=""

while IFS= read -r line || [[ -n "$line" ]]; do
    IFS='=' read -r envvar envval <<< "$line"
    args+="$envvar=$envval "
done < <(grep -vE '^[[:space:]]*($|#)' ".env.prod")

fly secrets set $args
