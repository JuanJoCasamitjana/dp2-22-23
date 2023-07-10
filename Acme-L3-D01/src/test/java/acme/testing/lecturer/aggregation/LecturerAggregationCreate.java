
package acme.testing.lecturer.aggregation;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class LecturerAggregationCreate extends TestHarness {

	final private String	lecturer3		= "lecturer3";
	private boolean			notCreated3		= true;
	final private String	lecturer5		= "lecturer5";
	private boolean			notCreated5		= true;
	final private String	lecturer6		= "lecturer6";
	private boolean			notCreated6		= true;
	final private String	lecturer12		= "lecturer12";
	private boolean			notCreated12	= true;
	private final String	lecturer13		= "lecturer13";
	private boolean			setUpTest1		= true;
	private char			titleStart		= 'Z';
	private char			codeStart		= 'Z';


	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/aggregation/cre-cour-lect-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive100CreateCourseAndLectureAndPublish(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {
		super.requestHome();
		if (this.notCreated3) {
			super.signUp(this.lecturer3, this.lecturer3, this.lecturer3, this.lecturer3, this.lecturer3 + "@mail.com");
			super.signIn(this.lecturer3, this.lecturer3);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer3);
			super.fillInputBoxIn("resume", this.lecturer3);
			super.fillInputBoxIn("listOfQualifications", this.lecturer3);
			super.clickOnSubmit("Register");
			super.signOut();
			this.notCreated3 = false;
		}
		super.signIn(this.lecturer3, this.lecturer3);
		super.clickOnMenu("Lecturer", "Create courses");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "Create lectures");
		super.checkFormExists();
		super.fillInputBoxIn("title", titleLecture);
		super.fillInputBoxIn("abstractMessage", abstractMessageLecture);
		super.fillInputBoxIn("learningTime", learningTime);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("theoretical", isTheoretical);
		super.fillInputBoxIn("optionalUrl", optionalUrlLecture);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.clickOnLink("Add to course");

		super.clickOnSubmit("Create");
		super.clickOnMenu("Lecturer", "See courses with lectures");
		super.checkListingExists();
		super.checkColumnHasValue(0, 0, code);
		super.checkColumnHasValue(0, 1, titleLecture);

		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.clickOnMenu("Lecturer", "List courses");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.checkNotFormExists();
		super.requestHome();
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/aggregation/cre-cour-lect-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative200CreateCourseAndLectureAndPublish(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {
		if (this.notCreated5) {
			super.signUp(this.lecturer5, this.lecturer5, this.lecturer5, this.lecturer5, this.lecturer5 + "@mail.com");
			super.signIn(this.lecturer5, this.lecturer5);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer5);
			super.fillInputBoxIn("resume", this.lecturer5);
			super.fillInputBoxIn("listOfQualifications", this.lecturer5);
			super.clickOnSubmit("Register");
			super.signOut();
			this.notCreated5 = false;
		}

		super.requestHome();
		super.signIn(this.lecturer5, this.lecturer5);
		super.clickOnMenu("Lecturer", "Create courses");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "Create lectures");
		super.checkFormExists();
		super.fillInputBoxIn("title", titleLecture);
		super.fillInputBoxIn("abstractMessage", abstractMessageLecture);
		super.fillInputBoxIn("learningTime", learningTime);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("theoretical", isTheoretical);
		super.fillInputBoxIn("optionalUrl", optionalUrlLecture);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.clickOnLink("Add to course");

		super.clickOnSubmit("Create");
		super.clickOnMenu("Lecturer", "See courses with lectures");
		super.checkListingExists();
		super.checkColumnHasValue(0, 0, code);
		super.checkColumnHasValue(0, 1, titleLecture);

		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.clickOnMenu("Lecturer", "List courses");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.checkErrorsExist();
		super.requestHome();
		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/aggregation/cre-cour-lect-negative-lnp.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void negative300CreateCourseAndLectureAndPublishButLectureNotPublished(final int recordIndex, final String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String isTheoretical, final String optionalUrlLecture) {

		if (this.notCreated6) {
			super.signUp(this.lecturer6, this.lecturer6, this.lecturer6, this.lecturer6, this.lecturer6 + "@mail.com");
			super.signIn(this.lecturer6, this.lecturer6);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer6);
			super.fillInputBoxIn("resume", this.lecturer6);
			super.fillInputBoxIn("listOfQualifications", this.lecturer6);
			super.clickOnSubmit("Register");
			super.signOut();
			this.notCreated6 = false;
		}

		super.requestHome();
		super.signIn(this.lecturer6, this.lecturer6);
		super.clickOnMenu("Lecturer", "Create courses");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "Create lectures");
		super.checkFormExists();
		super.fillInputBoxIn("title", titleLecture);
		super.fillInputBoxIn("abstractMessage", abstractMessageLecture);
		super.fillInputBoxIn("learningTime", learningTime);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("theoretical", isTheoretical);
		super.fillInputBoxIn("optionalUrl", optionalUrlLecture);
		super.clickOnSubmit("Create");

		super.requestHome();
		super.clickOnMenu("Lecturer", "List lectures");
		super.clickOnListingRecord(0);
		super.clickOnLink("Add to course");

		super.clickOnSubmit("Create");
		super.clickOnMenu("Lecturer", "See courses with lectures");
		super.checkListingExists();
		super.checkColumnHasValue(0, 0, code);
		super.checkColumnHasValue(0, 1, titleLecture);

		super.clickOnMenu("Lecturer", "List courses");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");

		super.checkErrorsExist();
		super.requestHome();
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/aggregation/cre-cour-lect-positive-typeupdt.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive500CourseTypeUpdate(final int recordIndex, String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String optionalUrlLecture, final String type) {
		String isTheoretical;
		code = this.codeStart + code;
		if (this.notCreated12) {
			super.signUp(this.lecturer12, this.lecturer12, this.lecturer12, this.lecturer12, this.lecturer12 + "@mail.com");
			super.signIn(this.lecturer12, this.lecturer12);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer12);
			super.fillInputBoxIn("resume", this.lecturer12);
			super.fillInputBoxIn("listOfQualifications", this.lecturer12);
			super.clickOnSubmit("Register");
			super.signOut();
			this.notCreated12 = false;
		}

		super.requestHome();
		super.signIn(this.lecturer12, this.lecturer12);
		super.clickOnMenu("Lecturer", "Create courses");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");

		for (int i = 0; i < 4; ++i) {

			switch (type) {
			case "PURELY_THEORETICAL":
				isTheoretical = "true";
				break;
			case "THEORETICAL":
				isTheoretical = "true";
				if (i == 3)
					isTheoretical = "false";
				break;
			case "EQUILIBRATED":
				isTheoretical = "true";
				if (i % 2 == 0)
					isTheoretical = "false";
				break;
			case "HANDS_ON":
				isTheoretical = "false";
				break;
			default:
				isTheoretical = "true";
				break;
			}
			super.requestHome();
			super.clickOnMenu("Lecturer", "Create lectures");
			super.checkFormExists();
			super.fillInputBoxIn("title", this.titleStart + titleLecture);
			super.fillInputBoxIn("abstractMessage", abstractMessageLecture);
			super.fillInputBoxIn("learningTime", learningTime);
			super.fillInputBoxIn("body", body);
			super.fillInputBoxIn("theoretical", isTheoretical);
			super.fillInputBoxIn("optionalUrl", optionalUrlLecture);
			super.clickOnSubmit("Create");

			super.requestHome();
			super.clickOnMenu("Lecturer", "List lectures");
			super.clickOnListingRecord(0);
			super.clickOnLink("Add to course");
			super.fillInputBoxIn("course", code);
			super.clickOnSubmit("Create");
			super.clickOnMenu("Lecturer", "List lectures");
			super.clickOnListingRecord(0);
			super.clickOnSubmit("Publish");
			this.titleStart--;
		}
		super.clickOnMenu("Lecturer", "List courses");
		super.clickOnListingRecord(0);
		super.checkInputBoxHasValue("typeOfCourse", type);
		super.requestHome();
		super.signOut();
		this.codeStart--;
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/lecturer/aggregation/cre-cour-lect-positive-typeupdt2.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void positive600CourseTypeLectureUpdate(final int recordIndex, String code, final String title, final String abstractMessage, //
		final String retailPrice, final String optionalUrl, final String titleLecture, final String abstractMessageLecture, //
		final String learningTime, final String body, final String optionalUrlLecture, final String type) {
		if (this.setUpTest1) {
			super.signUp(this.lecturer13, this.lecturer13, this.lecturer13, this.lecturer13, this.lecturer13 + "@mail.com");
			super.signIn(this.lecturer13, this.lecturer13);
			super.clickOnMenu("Account", "Become a Lecturer");
			super.fillInputBoxIn("almaMater", this.lecturer13);
			super.fillInputBoxIn("resume", this.lecturer13);
			super.fillInputBoxIn("listOfQualifications", this.lecturer13);
			super.clickOnSubmit("Register");
			super.signOut();
			this.codeStart = 'Z';
			this.titleStart = 'Z';
			this.setUpTest1 = false;
		}
		final String isTheoretical = "true";
		code = this.codeStart + code;
		super.requestHome();
		super.signIn(this.lecturer13, this.lecturer13);
		super.clickOnMenu("Lecturer", "Create courses");
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("abstractMessage", abstractMessage);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("optionalUrl", optionalUrl);
		super.clickOnSubmit("Create");
		//Create lectures and link them
		for (int i = 0; i < 4; ++i) {
			super.requestHome();
			super.clickOnMenu("Lecturer", "Create lectures");
			super.checkFormExists();
			super.fillInputBoxIn("title", this.titleStart + titleLecture);
			super.fillInputBoxIn("abstractMessage", abstractMessageLecture);
			super.fillInputBoxIn("learningTime", learningTime);
			super.fillInputBoxIn("body", body);
			super.fillInputBoxIn("theoretical", isTheoretical);
			super.fillInputBoxIn("optionalUrl", optionalUrlLecture);
			super.clickOnSubmit("Create");

			super.requestHome();
			super.clickOnMenu("Lecturer", "List lectures");
			super.clickOnListingRecord(0);
			super.clickOnLink("Add to course");
			super.fillInputBoxIn("course", code);
			super.clickOnSubmit("Create");
			this.titleStart--;
		}
		super.clickOnMenu("Lecturer", "List courses");
		super.clickOnListingRecord(0);
		super.checkInputBoxHasValue("typeOfCourse", "PURELY_THEORETICAL");

		final List<String> cases = Arrays.asList("PURELY_THEORETICAL", "THEORETICAL", "EQUILIBRATED", "HANDS_ON");
		for (final String c : cases)
			switch (c) {
			case "HANDS_ON":
				super.clickOnMenu("Lecturer", "List lectures");
				super.clickOnListingRecord(0);
				super.fillInputBoxIn("theoretical", "false");
				super.clickOnSubmit("Update");
				super.clickOnMenu("Lecturer", "List lectures");
				super.clickOnListingRecord(1);
				super.fillInputBoxIn("theoretical", "false");
				super.clickOnSubmit("Update");
			case "EQUILIBRATED":
				super.clickOnMenu("Lecturer", "List lectures");
				super.clickOnListingRecord(2);
				super.fillInputBoxIn("theoretical", "false");
				super.clickOnSubmit("Update");
			case "THEORETICAL":
				super.clickOnMenu("Lecturer", "List lectures");
				super.clickOnListingRecord(3);
				super.fillInputBoxIn("theoretical", "false");
				super.clickOnSubmit("Update");
			default:
				super.clickOnMenu("Lecturer", "List courses");
				super.clickOnListingRecord(0);
				super.checkInputBoxHasValue("typeOfCourse", c);
				break;
			case "PURELY_THEORETICAL":
				super.clickOnMenu("Lecturer", "List lectures");
				super.clickOnListingRecord(3);
				super.fillInputBoxIn("theoretical", "true");
				super.clickOnSubmit("Update");
				super.clickOnMenu("Lecturer", "List courses");
				super.clickOnListingRecord(0);
				super.checkInputBoxHasValue("typeOfCourse", c);
				break;
			}
		this.codeStart--;
	}
}
