package com.michael.graph.test;

import com.michael.graph.Edge;
import com.michael.graph.impl.GraphImpl;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphImplTest {

    @Test
    void testGetPath() {
        //given some undirected graph
        GraphImpl<String> stringGraph = new GraphImpl<>(false);
        stringGraph.addEdge("1", "2");
        stringGraph.addEdge("1", "3");
        stringGraph.addEdge("2", "4");
        stringGraph.addEdge("4", "8");
        stringGraph.addEdge("2", "2");
        stringGraph.addEdge("3", "5");
        stringGraph.addEdge("5", "8");

        //when search path that exists
        List<Edge<String>> existingPath = stringGraph.getPath("1", "8");
        //then path should be found
        assertEquals(
                asList(new Edge<>("1", "2"), new Edge<>("2", "4"), new Edge<>("4", "8")), existingPath);

        //when search for the same path reverse
        List<Edge<String>> existingReversePath = stringGraph.getPath("8", "1");
        //then path should be found
        assertEquals(
                asList(new Edge<>("8", "4"), new Edge<>("4", "2"), new Edge<>("2", "1")), existingReversePath);

        //when search for path that end not exists
        List<Edge<String>> notExistingEndPath = stringGraph.getPath("1", "11");
        //then path is empty
        assertEquals(0, notExistingEndPath.size());

        //when search for path that start not exists
        List<Edge<String>> notExistingStartPath = stringGraph.getPath("11", "2");
        //then path is empty
        assertEquals(0, notExistingStartPath.size());

        //when search for path where start and end the same
        List<Edge<String>> startStartPath = stringGraph.getPath("3", "3");
        //then returned edge with the same end and start
        assertEquals(Collections.singletonList(new Edge<>("3", "3")), startStartPath);
    }

    @Test
    void testNull(){
        GraphImpl<String> stringGraph = new GraphImpl<>(false);
        assertEquals("start.vertex.invalid",
                assertThrows(NullPointerException.class, () -> stringGraph.getPath(null, "3")).getMessage());
        assertEquals("end.vertex.invalid",
                assertThrows(NullPointerException.class, () -> stringGraph.getPath("3", null)).getMessage());
        assertEquals("start.vertex.invalid",
                assertThrows(NullPointerException.class, () -> stringGraph.addEdge(null, "3")).getMessage());
        assertEquals("end.vertex.invalid",
                assertThrows(NullPointerException.class, () -> stringGraph.addEdge("3", null)).getMessage());
        assertEquals("vertex.invalid",
                assertThrows(NullPointerException.class, () -> stringGraph.addVertex(null)).getMessage());
    }

}