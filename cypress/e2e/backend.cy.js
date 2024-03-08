const apiUrl = `${Cypress.env("apiUrl")}`

describe('Backend Test Spec', () => {

  it('should call ping', () => {
    cy.request({
      failOnStatusCode: false,
      method: 'GET',
      url: `${apiUrl}/ping`,
    }).then((response) => {
      expect(response.status).to.eq(200)
    })
  })
})



describe('Backend Test Spec', () => {

  it('should create a transaction', () => {
    const requestData = {
      accountId: '550e8400-e29b-41d4-a716-446655440000',
      amount: 100 
    };
    cy.request({
      failOnStatusCode: false,
      method: 'POST',
      url: `${apiUrl}/transaction`,
      body: requestData
    }).then((response) => {
     
      expect(response.status).to.eq(200);
    })
  })
})

