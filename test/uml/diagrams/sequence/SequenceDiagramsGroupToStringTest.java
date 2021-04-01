package uml.diagrams.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uml.diagrams.sequence.exceptions.EmptyOptionalFragmentException;
import uml.diagrams.sequence.exceptions.MessageFormatException;
import uml.diagrams.sequence.exceptions.SequenceDiagramRuleException;
import uml.diagrams.sequence.fragments.Optional;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.SequenceDiagram;
import uml.diagrams.sequence.sequencediagrams.messages.Message;

public class SequenceDiagramsGroupToStringTest {
    private final static String DEFAULT_NAME = "default";
    private final static Float DEFAULT_PROB = 0.5f;

    private SequenceDiagramsGroup sequenceDiagramsGroup;

    @BeforeEach
    public void setup() {
        sequenceDiagramsGroup = new SequenceDiagramsGroup();
    }

    @Test
    public void testToString1() {
        String expectedResult =
                "<SequenceDiagrams>" +
                        "<Lifelines></Lifelines>" +
                        "<Fragments></Fragments>" +
                "</SequenceDiagrams>";

        assertEquals(expectedResult, sequenceDiagramsGroup.toString());
    }
    
    @Test
    public void testToString2() throws SequenceDiagramRuleException,
        EmptyOptionalFragmentException, MessageFormatException {
        
        SequenceDiagram sequenceDiagram1 = new SequenceDiagram("diagram1", true);
        Lifeline lifeline = new Lifeline(DEFAULT_NAME);
        Optional optional1 = new Optional(DEFAULT_NAME, sequenceDiagram1);
        
        sequenceDiagramsGroup.addLifeline(lifeline);
        sequenceDiagramsGroup.addOptional(optional1);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram1);

        String expectedResult =
                "<SequenceDiagrams>" +
                        "<Lifelines>" +
                            lifeline.toString() +
                        "</Lifelines>" +
                        "<Fragments>" +
                            optional1.toString() +
                        "</Fragments>" +
                        sequenceDiagram1.toString() +
                "</SequenceDiagrams>";

        assertEquals(expectedResult, sequenceDiagramsGroup.toString());
    }
    
    @Test
    public void testToString3() throws SequenceDiagramRuleException,
        EmptyOptionalFragmentException, MessageFormatException {
        
        SequenceDiagram sequenceDiagram1 = new SequenceDiagram("diagram1", true);
        SequenceDiagram sequenceDiagram2 = new SequenceDiagram("diagram2", false);
        Lifeline lifeline = new Lifeline(DEFAULT_NAME);
        Optional optional1 = new Optional(DEFAULT_NAME, sequenceDiagram1);
        Message message1 = new Message(DEFAULT_NAME, DEFAULT_PROB, lifeline, lifeline);
        
        sequenceDiagram1.addMessage(message1);
        
        sequenceDiagramsGroup.addLifeline(lifeline);
        sequenceDiagramsGroup.addOptional(optional1);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram1);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram2);

        String expectedResult =
                "<SequenceDiagrams>" +
                        "<Lifelines>" +
                            lifeline.toString() +
                        "</Lifelines>" +
                        "<Fragments>" +
                            optional1.toString() +
                        "</Fragments>" +
                        sequenceDiagram1.toString() +
                        sequenceDiagram2.toString() +
                "</SequenceDiagrams>";

        assertEquals(expectedResult, sequenceDiagramsGroup.toString());
    }
    
    @Test
    public void testToString4() throws SequenceDiagramRuleException,
        EmptyOptionalFragmentException, MessageFormatException {
        
        SequenceDiagram sequenceDiagram1 = new SequenceDiagram("diagram1", true);
        SequenceDiagram sequenceDiagram2 = new SequenceDiagram("diagram2", false);
        Lifeline lifeline1 = new Lifeline(DEFAULT_NAME);
        Lifeline lifeline2 = new Lifeline(DEFAULT_NAME);
        Optional optional1 = new Optional(DEFAULT_NAME, sequenceDiagram1);
        Message message1 = new Message(DEFAULT_NAME, DEFAULT_PROB, lifeline1, lifeline1);
        Message message2 = new Message(DEFAULT_NAME, DEFAULT_PROB, lifeline2, lifeline1);
        Message message3 = new Message(DEFAULT_NAME, DEFAULT_PROB, lifeline2, lifeline1);
        
        sequenceDiagram1.addMessage(message1);
        sequenceDiagram1.addMessage(message2);
        sequenceDiagram2.addMessage(message3);
        
        sequenceDiagramsGroup.addLifeline(lifeline1);
        sequenceDiagramsGroup.addOptional(optional1);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram1);
        sequenceDiagramsGroup.addSequenceDiagram(sequenceDiagram2);

        String expectedResult =
                "<SequenceDiagrams>" +
                        "<Lifelines>" +
                            lifeline1.toString() +
                        "</Lifelines>" +
                        "<Fragments>" +
                            optional1.toString() +
                        "</Fragments>" +
                        sequenceDiagram1.toString() +
                        sequenceDiagram2.toString() +
                "</SequenceDiagrams>";

        assertEquals(expectedResult, sequenceDiagramsGroup.toString());
    }
}