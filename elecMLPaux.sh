#!/bin/bash
awk -F "\"*,\"*" '{print $1}' data/elecdata/elec2MLP/elec2MLP1.dump > data/elecdata/elec2MLP/elec2MLPaux.dump
for i in `seq 1 30`;
do
    awk -F "\"*,\"*" '{print $5}' data/elecdata/elec2MLP/elec2MLP$i.dump > data/elecdata/elec2MLP/elec2MLPaux$i.dump
done
paste -d ',' data/elecdata/elec2MLP/elec2MLPaux.dump data/elecdata/elec2MLP/elec2MLPaux1.dump data/elecdata/elec2MLP/elec2MLPaux2.dump data/elecdata/elec2MLP/elec2MLPaux3.dump data/elecdata/elec2MLP/elec2MLPaux4.dump data/elecdata/elec2MLP/elec2MLPaux5.dump data/elecdata/elec2MLP/elec2MLPaux6.dump data/elecdata/elec2MLP/elec2MLPaux7.dump data/elecdata/elec2MLP/elec2MLPaux8.dump data/elecdata/elec2MLP/elec2MLPaux9.dump data/elecdata/elec2MLP/elec2MLPaux10.dump data/elecdata/elec2MLP/elec2MLPaux11.dump data/elecdata/elec2MLP/elec2MLPaux12.dump data/elecdata/elec2MLP/elec2MLPaux13.dump data/elecdata/elec2MLP/elec2MLPaux14.dump data/elecdata/elec2MLP/elec2MLPaux15.dump data/elecdata/elec2MLP/elec2MLPaux16.dump data/elecdata/elec2MLP/elec2MLPaux17.dump data/elecdata/elec2MLP/elec2MLPaux18.dump data/elecdata/elec2MLP/elec2MLPaux19.dump data/elecdata/elec2MLP/elec2MLPaux20.dump data/elecdata/elec2MLP/elec2MLPaux21.dump data/elecdata/elec2MLP/elec2MLPaux22.dump data/elecdata/elec2MLP/elec2MLPaux23.dump data/elecdata/elec2MLP/elec2MLPaux24.dump data/elecdata/elec2MLP/elec2MLPaux25.dump data/elecdata/elec2MLP/elec2MLPaux26.dump data/elecdata/elec2MLP/elec2MLPaux27.dump data/elecdata/elec2MLP/elec2MLPaux28.dump data/elecdata/elec2MLP/elec2MLPaux29.dump data/elecdata/elec2MLP/elec2MLPaux30.dump > data/elecdata/elec2MLP/elec2MLP.dump
rm data/elecdata/elec2MLP/elec2MLPaux.dump
for i in `seq 1 30`;
do
    rm data/elecdata/elec2MLP/elec2MLPaux$i.dump
done
