HR2  (not the Seven Golden Rules itself) 
is based on code of HiRes from Joerg Hau which is published under the GPL

HR2 compiles under Windows with Visual C++ Express (freely available) at:
http://msdn.microsoft.com/vstudio/express/

There are two versions:
a) a formula generator which generates output, by using ">" the results can be written into a file
b) a counting only version

A)
hr2-all-res -C "ActinomycinD-5ppm" -m 1254.629124 -t 6.278182 -C 1-95 -H 1-190 -N 0-20 -O 0-80 -P 0-12 -S 0-9 
*** Result: 2274 formulas found in     14 seconds by evaluating 689753865 formulae.

hr2-all-res -c "BrClS-10_1000Da_20ppm_20iae" -m 1000 -t 20 -C 38-84 -H 0-188 -N 0-40 -O 0-63 -P 0-12 -S 0-12 -F 0-12 -L 0-14 -B 0-10 -I 0-0  
*** Result: 130812 formulas found in     33 seconds by evaluating 434674243 formulae.

B) the counting version starts with a low mass (-m) but uses a large mass limit (-t)
hr2-fast-all-res-count -c "test" -m 1 -t 1000000  -C 1-78 -H 1-126 -N 0-25 -O 0-27 -P 0-9 -S 0-14
*** Result: 30077741 formulas found in     23 seconds by evaluating 240065734 formulae.


Syntax:
-C : comment
-m : accurate isotopic mass
-t : mass limit (mmu) (not ppm!)
-C : is number or range of chlorine atoms
-H : is number or range of hydrogen atoms
-N : is number or range of nitrogen atoms
-O : is number or range of oxigene atoms
-P : is number or range of phosphorous atoms
-S : is number or range of sulfur atoms
-F : is number or range of fluorine atoms
-L : is number or range of chlorine atoms
-B : is Brom (not Bor) (!)
-I : is silicon not iodine (!)

----------------------------------------------------------------------------------
LICENSE for HR2 (not the Seven Golden Rules itself)

This program and its documentation are Copyright (c) 1992-2005 by Joerg Hau.

This program is free software; you can redistribute it and/or modify it under
the terms of version 2 of the GNU General Public License ("GPL") as published
by the Free Software Foundation. See the file LICENSE for details.

If you use this program (or any part of it) in another application, note
that the resulting application becomes also GPL. In other words, GPL is a
"contaminating" license.

This program is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License (file
LICENSE) for more details.
----------------------------------------------------------------------------------



