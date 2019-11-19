package com.dwt.tddcata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class NamePollerTest {


    @Mock
    IRemoteConnector remoteConnector;

    @Mock
    INameRepository repository;


    @InjectMocks
    NamePoller poller;

    @Test
    void shouldAddAllIfNoRecordExistsInDb(){
        when(remoteConnector.getNames())
                .thenReturn(List.of("a","b","c"));
        poller.execute();
        verify(repository)
                .add(argThat(hasItems("a","b","c")));
    }

    @Test
    void shouldAddOnlyChangedOnes(){
        when(remoteConnector.getNames())
                .thenReturn(List.of("a","b","c"));
        when(repository.getAll())
                .thenReturn(List.of("a","d"));
        poller.execute();
        verify(repository)
                .add(argThat(hasItems("b","c")));
    }

    @Test
    void shouldRemoveFromDbIfRemovedInRemote(){
        when(remoteConnector.getNames())
                .thenReturn(List.of("a","b","c"));
        when(repository.getAll())
                .thenReturn(List.of("a","d"));
        poller.execute();
        verify(repository)
                .remove(argThat(hasItems("d")));
    }


    @Test
    void shouldNotCrushIfRemoteFails(){
        when(remoteConnector.getNames())
                .thenThrow(new RuntimeException());
        poller.execute();
    }

    private ArgumentMatcher<List<String>> hasItems(String ... items) {
        return argument -> {
            assertThat(argument)
                    .containsExactly(items);
            return true;
        };
    }
}