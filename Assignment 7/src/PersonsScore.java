public class PersonsScore implements Comparable<PersonsScore> {

	private final String name;
	private final int score;

	public PersonsScore(String name, int score) {
		this.name = name;
		this.score = score;
	}

	@Override
	public int compareTo(PersonsScore o) {
		int i = Integer.compare(o.score, this.score);
		if(i == 0)return this.name.compareTo(o.name);
		return i;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)return true;

		if(o instanceof PersonsScore) {
			PersonsScore that = (PersonsScore) o;
			return score == that.score && this.name.equals(that.name);
		} else if(o instanceof String) {
			//Hack to make the linked list work.
			return this.name.equals(o);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() * 31 + this.score;
	}

	@Override
	public String toString() {
		return "PersonsScore{" + "name='" + this.name + '\'' + ", score=" + this.score + '}';
	}

}
