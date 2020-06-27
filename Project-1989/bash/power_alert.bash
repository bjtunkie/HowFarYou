#!/bin/bash

declare -i power=0
power_command=$(upower -e | grep -i 'bat')
while true; do
  power=$(upower -i "${power_command}" | grep -i percentage | grep -oP '[0-9]*')
  state=$(upower -i "${power_command}" | grep -i state)

  if [[ "${state}" == *"discharging"* ]]; then
    ## 'Discharging...'
    if [[ "${power}" -lt 10 ]]; then
      ffplay -showmode 0 /home/surya/Music/God/BatteryLow.ogg -loglevel quiet -autoexit
      sleep 5
      continue
    fi
  else
    ## Charging...
    if [[ "${power}" -gt 98 ]]; then
      ffplay -showmode 0 /home/surya/Music/God/BatteryFull.ogg -loglevel quiet -autoexit
      sleep 5
      continue
    fi
  fi
  sleep 60
done
