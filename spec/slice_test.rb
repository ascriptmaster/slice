# Please have the server running on local before running these tests!
# Requires the gem "rest-client".
require 'restclient'
require 'json'

HOST_NAME = "http://localhost:4567/"

RSpec.describe "Server data" do
	it "should return an error if no name is specified" do
		response = RestClient.get HOST_NAME
		expect(response.code).to eql 200
		expect(JSON.parse(response.body)["error"]).not_to be_nil
	end

	it "should count the number of times a word was requested" do
		response = RestClient.get HOST_NAME + "?name=prenom"
		expect(response.code).to eql 200
		count = JSON.parse(response.body)["timesCalled"]
		response = RestClient.get HOST_NAME + "?name=prenom"
		expect(response.code).to eql 200
		expect(count + 1).to eql JSON.parse(response.body)["timesCalled"]
	end

	it "should count the number of times a word appears in files" do
		response = RestClient.get HOST_NAME + "?name=prenom"
		expect(response.code).to eql 200
		count = JSON.parse(response.body)["foundInstances"]
		expect(count).to eql 0

		# This relies on the text files in the email.
		response = RestClient.get HOST_NAME + "?name=stele"
		expect(response.code).to eql 200
		count = JSON.parse(response.body)["foundInstances"]
		expect(count).to eql 5
	end
end