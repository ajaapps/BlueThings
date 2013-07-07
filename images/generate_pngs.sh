#!/bin/bash -ex
# Copyright (C) 2013 Aja Apps
# under the terms of GPLv3.

cd images/
foreground=bluething
background=yellowpin

for infile in "${foreground}.svg" "${background}.svg" ; do
    
    outfile=${infile/svg/png}

    inkscape --vacuum-defs -w 144 -e ../res/drawable-xxhdpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-xxhdpi/$outfile

    inkscape --vacuum-defs -w 96 -e ../res/drawable-xhdpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-xhdpi/$outfile

    inkscape --vacuum-defs -w 72 -e ../res/drawable-hdpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-hdpi/$outfile

    inkscape --vacuum-defs -w 48 -e ../res/drawable-mdpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-mdpi/$outfile

    inkscape --vacuum-defs -w 36 -e ../res/drawable-ldpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-ldpi/$outfile

    inkscape --vacuum-defs -w 115 -e ../res/drawable-land-xxhdpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-land-xxhdpi/$outfile

    inkscape --vacuum-defs -w 76 -e ../res/drawable-land-xhdpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-land-xhdpi/$outfile

    inkscape --vacuum-defs -w 57 -e ../res/drawable-land-hdpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-land-hdpi/$outfile

    inkscape --vacuum-defs -w 38 -e ../res/drawable-land-mdpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-land-mdpi/$outfile

    inkscape --vacuum-defs -w 28 -e ../res/drawable-land-ldpi/$outfile $infile >/dev/null
    optipng -quiet ../res/drawable-land-ldpi/$outfile

done

cd ../res/
for suffix in xxhdpi xhdpi hdpi mdpi ldpi \
           land-xxhdpi land-xhdpi land-hdpi land-mdpi land-ldpi ; do
    cd drawable-${suffix}/
    convert -composite bluething.png yellowpin.png ic_appicon.png
    optipng -quiet ic_appicon.png
    cd ..
done
