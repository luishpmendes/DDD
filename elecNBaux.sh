#!/bin/bash
awk -F "\"*,\"*" '{print $1}' data/elecdata/elec2NB/elec2NB1.dump > data/elecdata/elec2NB/elec2NBaux.dump
for i in `seq 1 30`;
do
    awk -F "\"*,\"*" '{print $5}' data/elecdata/elec2NB/elec2NB$i.dump > data/elecdata/elec2NB/elec2NBaux$i.dump
done
paste -d ',' data/elecdata/elec2NB/elec2NBaux.dump data/elecdata/elec2NB/elec2NBaux1.dump data/elecdata/elec2NB/elec2NBaux2.dump data/elecdata/elec2NB/elec2NBaux3.dump data/elecdata/elec2NB/elec2NBaux4.dump data/elecdata/elec2NB/elec2NBaux5.dump data/elecdata/elec2NB/elec2NBaux6.dump data/elecdata/elec2NB/elec2NBaux7.dump data/elecdata/elec2NB/elec2NBaux8.dump data/elecdata/elec2NB/elec2NBaux9.dump data/elecdata/elec2NB/elec2NBaux10.dump data/elecdata/elec2NB/elec2NBaux11.dump data/elecdata/elec2NB/elec2NBaux12.dump data/elecdata/elec2NB/elec2NBaux13.dump data/elecdata/elec2NB/elec2NBaux14.dump data/elecdata/elec2NB/elec2NBaux15.dump data/elecdata/elec2NB/elec2NBaux16.dump data/elecdata/elec2NB/elec2NBaux17.dump data/elecdata/elec2NB/elec2NBaux18.dump data/elecdata/elec2NB/elec2NBaux19.dump data/elecdata/elec2NB/elec2NBaux20.dump data/elecdata/elec2NB/elec2NBaux21.dump data/elecdata/elec2NB/elec2NBaux22.dump data/elecdata/elec2NB/elec2NBaux23.dump data/elecdata/elec2NB/elec2NBaux24.dump data/elecdata/elec2NB/elec2NBaux25.dump data/elecdata/elec2NB/elec2NBaux26.dump data/elecdata/elec2NB/elec2NBaux27.dump data/elecdata/elec2NB/elec2NBaux28.dump data/elecdata/elec2NB/elec2NBaux29.dump data/elecdata/elec2NB/elec2NBaux30.dump > data/elecdata/elec2NB/elec2NB.dump
rm data/elecdata/elec2NB/elec2NBaux.dump
for i in `seq 1 30`;
do
    rm data/elecdata/elec2NB/elec2NBaux$i.dump
done
