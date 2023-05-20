
package acme.testing.any;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PeepListShow extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void listAndShow100positive(final int recordIndex, final String instantiation, final String title, final String nick, //
		final String message, final String mail, final String link) {

		super.requestHome();
		super.clickOnMenu("Any", "List peeps");

		super.checkListingExists();
		super.checkColumnHasValue(recordIndex, 0, nick);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, mail);
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("nick", nick);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("message", message);
		super.checkInputBoxHasValue("mail", mail);
		super.checkInputBoxHasValue("instantiation", instantiation);
		super.checkInputBoxHasValue("link", link);

	}

	public void createPositive() {

	}
	public void createNegative() {

	}
}
