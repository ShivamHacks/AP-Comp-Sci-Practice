public class ClassExtend {
	
	private String secret;

	public ClassExtend() {
		
	}

	public ClassExtend(String newSecret) {
		secret = newSecret;
	}

	public void print() {
		System.out.println(secret);
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String newSecret) {
		secret = newSecret;
	}

}