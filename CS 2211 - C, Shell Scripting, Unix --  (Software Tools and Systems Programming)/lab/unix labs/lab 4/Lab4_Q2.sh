#!/bin/sh
X=$1
echo $X
shift
while [ $# -gt 0 ]; do
  echo $1
  if [ $1 -gt $X ]; then
    X=$1
    echo �   << $X�
  fi
  shift
done
echo �-----�;  echo $X
