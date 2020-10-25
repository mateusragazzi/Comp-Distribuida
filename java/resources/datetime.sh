#!/bin/sh
printf 'Content-Type: text/html\r\n\n';
dt=$(date '+%d/%m/%Y %H:%M:%S');
echo "<h2>$dt</h2>";