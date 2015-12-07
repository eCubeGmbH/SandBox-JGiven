package com.tngtech.jgiven.examples.nested;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.NestedSteps;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.junit.SimpleScenarioTest;
import org.junit.Test;
import org.testng.Assert;

public class NestedStepsTest extends SimpleScenarioTest<NestedStepsTest.NestedStage> {


    @Test
    public void aTestWithNestedSteps() {

        given().I_fill_out_the_registration_form_with_valid_values();

        when().I_submit_the_form();

        then().the_password_matches();

    }


    static class NestedStage extends Stage<NestedStage> {

        @ProvidedScenarioState
        String name;

        @ProvidedScenarioState
        String email;

        @ProvidedScenarioState
        String password;

        @ProvidedScenarioState
        String repeatedPassword;

        @NestedSteps
        public NestedStage I_fill_out_the_registration_form_with_valid_values() {
            return I_enter_a_name("Franky")
                    .and().I_enter_a_email_address("franky@acme.com")
                    .and().I_enter_a_password("password1234")
                    .and().I_enter_a_repeated_password("password1234");
        }

        @NestedSteps
        public NestedStage I_enter_a_name(String name) {
            return I_think_a_name(name).and().I_write_the_name(name);
        }

        public NestedStage I_think_a_name(String name) {
            return self();
        }

        public NestedStage I_write_the_name(String name) {
            this.name = name;
            return self();
        }

        public NestedStage I_enter_a_email_address(String email) {
            this.email = email;
            return self();
        }

        public NestedStage I_enter_a_password(String password) {
            this.password = password;
            return self();
        }

        public NestedStage I_enter_a_repeated_password(String repeatedPassword) {
            this.repeatedPassword = repeatedPassword;
            return self();
        }

        public NestedStage I_submit_the_form() {
            return self();
        }

        public NestedStage the_password_matches() {
            Assert.assertEquals(password, repeatedPassword);
            return self();
        }

    }


}
