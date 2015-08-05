#!/bin/bash
awk -F "\"*,\"*" '{print $1}' data/PAKDD/Modeling_DataNB/Modeling_DataNB1.dump > data/PAKDD/Modeling_DataNB/Modeling_DataNBaux.dump
for i in `seq 1 30`;
do
    awk -F "\"*,\"*" '{print $5}' data/PAKDD/Modeling_DataNB/Modeling_DataNB$i.dump > data/PAKDD/Modeling_DataNB/Modeling_DataNBaux$i.dump
done
paste -d ',' data/PAKDD/Modeling_DataNB/Modeling_DataNBaux.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux1.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux2.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux3.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux4.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux5.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux6.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux7.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux8.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux9.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux10.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux11.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux12.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux13.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux14.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux15.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux16.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux17.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux18.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux19.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux20.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux21.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux22.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux23.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux24.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux25.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux26.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux27.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux28.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux29.dump data/PAKDD/Modeling_DataNB/Modeling_DataNBaux30.dump > data/PAKDD/Modeling_DataNB/Modeling_DataNB.dump
rm data/PAKDD/Modeling_DataNB/Modeling_DataNBaux.dump
for i in `seq 1 30`;
do
    rm data/PAKDD/Modeling_DataNB/Modeling_DataNBaux$i.dump
done
