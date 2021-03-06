package uml.diagrams.sequence.sequencediagrams.messages;

import uml.diagrams.sequence.exceptions.*;
import uml.diagrams.sequence.lifelines.Lifeline;
import uml.diagrams.sequence.sequencediagrams.ISequenceDiagramElement;

public class Message implements ISequenceDiagramElement {
    static final String INVALID_NAME_ERROR_MESSAGE = "O nome não pode ser vazio ou nulo";
	static final String INVALID_PROB_VALUE_ERROR_MESSAGE = "A prob deve ser um valor real entre 0 e 1";
	static final String INVALID_SOURCE = "O valor de origem não pode ser nulo";
	static final String INVALID_TARGET = "O valor de destino não pode ser nulo";
    
    protected String name;
    protected Float prob = 0.0f;
    protected Lifeline source;
    protected Lifeline target;
	
	public Message(String name, Float prob, Lifeline source, Lifeline target)
	        throws MessageFormatException {
		setName(name);
		setProb(prob);
		setSource(source);
		setTarget(target);
	}

	public String getName() {
        return this.name;
    }
    
    public void setName(String name) throws MessageFormatException {
        if (name == null || name.trim().isEmpty())
            throw new MessageFormatException(INVALID_NAME_ERROR_MESSAGE);
        this.name = name;
    }

	public Float getProb() {
		return prob;
	}

	public void setProb(Float prob) throws MessageFormatException {
	    if (prob == null || prob > 1.0f || prob < 0.0f) {            
            throw new MessageFormatException(INVALID_PROB_VALUE_ERROR_MESSAGE);
        }

		this.prob = prob;
	}
	
	public Lifeline getSource() {
		return source;
	}
	
	public void setSource(Lifeline source) throws MessageFormatException {
	    if (source == null) {
	        throw new MessageFormatException(INVALID_SOURCE);
	    }
	    
		this.source = source;
	}
	
	public Lifeline getTarget() {
		return target;
	}
	
	public void setTarget(Lifeline target) throws MessageFormatException {
	    if (target == null) {
	        throw new MessageFormatException(INVALID_TARGET);
	    }

		this.target = target;
	}

	@Override
	public String toString() {
		return "<Message " + 
				"name=\"" + this.getName() + "\" " + 
				"prob=\"" + this.prob + "\" " + 
				"source=\"" + this.getSource().getName() + "\" " +
				"target=\"" + this.getTarget().getName() + "\" " +
				"/>";				
	}
}
