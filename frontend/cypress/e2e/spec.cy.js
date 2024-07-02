URL = "http://localhost:8081"
const marioId = "106"
const email = "super@mario.com"
const pw = "Password01!"


// describe('Login Test', () => {

//   it("Confirms data validation during registration", () => {

//     cy.visit(URL + "/register")

//     // Check that the error message fields are initially empty, as they should be
//     cy.get('#input-0-messages').should("be.empty");
//     cy.get('#input-2-messages').should("be.empty");

//     // Type some invalid data into the input fields
//     cy.get("#input-0").type("thisIsNot_aValidEmail")
//     cy.get("#input-2").type("notComplexEnoughPassword")
//     cy.get("#input-4").type("notComplexEnoughPassword")

//     // Check that the error messages are now present, indicating we submitted invalid data
//     cy.get('#input-0-messages').should("not.be.empty");
//     cy.get('#input-2-messages').should("not.be.empty");
//   })

//   it('Confirms that login works', () => {

//     // There are some issues with getting the input field by its id (using #input-2 yields the password input field, while it should yield the email input field instead)
//     // Because of this, we simply loop over everything containing 'input-' in its name, and only type something in the first item that is returned.
//     // The first item that is returned is always the email input field, as it appears earlier on in the page.
//     // Bit of an ugly workaround but necessary because looking for the input field ID doesn't seem to work well.
//     cy.visit(URL + "/login")
//     cy.get("#input-2").type(pw) // This *should* return the input field for the e-mail, but for some reason it gets us to the input field of the password...
//     cy.get("[id^=input-]")
//     .each(($ele, index) => {
//       if(index == 0) {
//         cy.get($ele).type(email)
//       }
//     })
    
//     cy.get("[type^=submit]").click();

//     cy.url().should("equal", URL + "/")
//   })

// })

describe('Upload sample test', () => {

  beforeEach(() => {
    // Login
    cy.visit(URL + "/login");
    cy.get("#input-2").type(pw) // This *should* return the input field for the e-mail, but for some reason it gets us to the input field of the password...
    cy.get("[id^=input-]")
    .each(($ele, index) => {
      if(index == 0) {
        cy.get($ele).type(email)
      }
    })
    cy.get('button[type=submit]').click();
    cy.screenshot();
    cy.visit(URL + "/profile" + "/" + marioId, {
      onBeforeLoad(win) {
        cy.stub(win.console, 'log').as('consoleLog');
      },
    })
  });


  it("Confirms uploading of music sample works", () => {
    
    cy.get("[id=openUploadButton]").click();
    
    cy.get('input[type=file]').attachFile({
      filePath: 'music_sample.wav',
      encoding: 'base64',
    });

    cy.wait(500)
    
    cy.get("[id=uploadButton]").click();

    cy.get('@consoleLog').should('be.calledWith', 'all files uploaded');
    
  })

  it("Confirms uploading of image as music sample doesnt work", () => {
    
    cy.get("[id=openUploadButton]").click();
    
    cy.get('input[type=file]').attachFile({
      filePath: 'invalid_sample.jpg',
      encoding: 'base64',
    });

    cy.wait(500)
    
    cy.get("[id=uploadButton]").click();

    cy.get('@consoleLog').should('not.be.calledWith', 'all files uploaded');

  })

})


/*

describe("Profile Test", () => {
  it("Confirms that profile data is loaded", () => {


    //cy.visit(URL + "/profile?idUser=" + marioId)

    cy.visit(URL + "/profile" + "/" + marioId)

    
    cy.request({
      method: "GET",
      url: URL + "/profile",
      qs: {
        "id": marioId
      }
    })
    
  })
})



describe("Explore Page Test", () => {
  it("Confirms the explore page is accessible without logging in", () => {

    cy.visit(URL + "/" + marioId)

    cy.visit({
      url: URL,
      qs: {
        "id": marioId
      }
    })

    /*
    cy.request({
      method: "GET",
      url: URL + "/profile",
      qs: {
        "id": marioId
      }
    })
    
  })
})
*/