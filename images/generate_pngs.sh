#!/bin/bash -ex
# Copyright (C) 2013 Aja Apps
# under the terms of the WTFPL.

cd images/
foreground=bluething
background=yellowpin

for infile in "${foreground}.svg" "${background}.svg" ; do
    outfile=${infile/svg/png}
    inkscape --vacuum-defs -w 144 -e ../res/drawable-xxhdpi/$outfile $infile >/dev/null
    inkscape --vacuum-defs -w 96 -e ../res/drawable-xhdpi/$outfile $infile >/dev/null
    inkscape --vacuum-defs -w 72 -e ../res/drawable-hdpi/$outfile $infile >/dev/null
    inkscape --vacuum-defs -w 48 -e ../res/drawable-mdpi/$outfile $infile >/dev/null
    inkscape --vacuum-defs -w 36 -e ../res/drawable-ldpi/$outfile $infile >/dev/null
done

cd ../res/
for suffix in xxhdpi xhdpi hdpi mdpi ldpi ; do
    cd drawable-${suffix}/
    convert -composite bluething.png yellowpin.png composite.png
    optipng -quiet yellowpin.png composite.png
    rm bluething.png
    cd ..
done
