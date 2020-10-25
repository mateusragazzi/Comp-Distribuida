#!/bin/sh
printf 'Content-Type: text/html\r\n\n';
dt=$(date '+%d/%m/%Y %H:%M:%S');
echo "<div><p>Hello ${USERNAME}</p><p>$dt</p></div>";