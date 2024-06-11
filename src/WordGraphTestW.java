

import org.junit.Before;
import org.junit.Test;
import demo.*;

import static demo.Main.readFileContent;
import static org.junit.Assert.*;
public class WordGraphTestW {

    private WordGraph wordGraph;
    @Before
    public void setUp() throws Exception {

        String filePath = "./src/test.txt";

        wordGraph = new WordGraph();
        String processedContent = readFileContent(filePath);
        wordGraph.buildGraph(processedContent);
        Graphviz.showDirectedGraph(wordGraph);
        System.out.println("setUp successful");
    }

    @Test
    public void testQueryBridgeWords_bothWordsNotInGraph() {
        String result = wordGraph.queryBridgeWords("hahaha", "huhuhu");
        assertEquals("Neither hahaha nor huhuhu is in the graph!", result);
    }

    @Test
    public void testQueryBridgeWords_word1NotInGraph() {
        String result = wordGraph.queryBridgeWords("hahahaha", "birds");
        assertEquals("No hahahaha in the graph!", result);
    }

    @Test
    public void testQueryBridgeWords_word2NotInGraph() {
        String result = wordGraph.queryBridgeWords("birds", "huhuhuhu");
        assertEquals("No huhuhuhu in the graph!", result);
    }

    @Test
    public void testQueryBridgeWords_withBridgeWords() {
        String result = wordGraph.queryBridgeWords("birds", "feathers");
        assertEquals("The bridge words from birds to feathers are:with", result);
    }

    @Test
    public void testQueryBridgeWords_noBridgeWords() {
        String result = wordGraph.queryBridgeWords("birds", "with");
        assertEquals("No bridge words from birds to with!", result);
    }
}