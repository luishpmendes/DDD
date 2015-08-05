#!/bin/bash
awk -F "\"*,\"*" '{print $1}' data/PAKDD/Modeling_DataMLP/Modeling_DataMLP1.dump > data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux.dump
for i in `seq 1 30`;
do
    awk -F "\"*,\"*" '{print $5}' data/PAKDD/Modeling_DataMLP/Modeling_DataMLP$i.dump > data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux$i.dump
done
paste -d ',' data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux1.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux2.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux3.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux4.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux5.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux6.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux7.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux8.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux9.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux10.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux11.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux12.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux13.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux14.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux15.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux16.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux17.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux18.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux19.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux20.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux21.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux22.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux23.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux24.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux25.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux26.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux27.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux28.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux29.dump data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux30.dump > data/PAKDD/Modeling_DataMLP/Modeling_DataMLP.dump
rm data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux.dump
for i in `seq 1 30`;
do
    rm data/PAKDD/Modeling_DataMLP/Modeling_DataMLPaux$i.dump
done
