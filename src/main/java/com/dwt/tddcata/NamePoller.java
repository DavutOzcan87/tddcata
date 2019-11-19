package com.dwt.tddcata;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NamePoller
{
    IRemoteConnector connector;
    INameRepository nameRepository;


    public NamePoller(IRemoteConnector connector, INameRepository nameRepository) {
        this.connector = connector;
        this.nameRepository = nameRepository;
    }

    void execute(){
        try{
            executeUnsafe();
        }catch (Exception e){
            System.err.println(e);
        }
    }

    private void executeUnsafe() {
        var remotes = connector.getNames();
        var dbOnes = nameRepository.getAll();

        var diff = remotes.stream()
                .filter(item -> !dbOnes.contains(item) )
                .collect(Collectors.toList());
        var removed = dbOnes.stream()
                .filter(Predicate.not(remotes::contains))
                .collect(Collectors.toList());
        nameRepository.add(diff);
        nameRepository.remove(removed);
    }
}
