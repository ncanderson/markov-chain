package markov_chain.model;

import java.util.HashMap;
import java.util.Map.Entry;

class State<T> {

	T value;
	
	public int transitionCount = 0;
	
	public HashMap<State<T>, Integer> transitions = new HashMap<State<T>, Integer>();

	State(T value) {
		this.value = value;
	}

	public void addTransition(State<T> state) {
		Integer i = transitions.get(state);
		if (i == null) {
			i = Integer.valueOf(0);
		}
		transitions.put(state, Integer.valueOf(i + 1));
		transitionCount++;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String orig;
		if( value == null ){
			orig = "START";
		} else {
			orig = value.toString();
		}
		for ( Entry<State<T>, Integer> entry: transitions.entrySet() ) {
			State<T> state = entry.getKey();
			String end;
			if (state.value != null) {
				end = state.value.toString();
			} else {
				end = "END";
			}
			sb.append(String.format("%s -> %s [label = \"%.4f\"];\n", orig, end, ((float)entry.getValue().intValue() / (float)transitionCount)  ));
		}
		return sb.toString();
	}
}