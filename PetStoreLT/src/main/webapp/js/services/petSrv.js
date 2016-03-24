'use strict';

angular.module('petStoreLTApp')

/**
 * @ngdoc service
 * @name petSrv
 * @description This service provides methods to access <i>pet</i>s.
 */
.service('petSrv', function($http, GLOBAL) {
	
	var service = {};
		
	console.log(GLOBAL.endpoint);
	
	service.addPet = function(pet, successFn, errorFn) {
		console.log('add pet ' + pet.name);
		$http.post(GLOBAL.endpoint + 'pet', pet).then(successFn, errorFn);
	};

	service.removePet = function(petId, successFn, errorFn) {
		$http({
			method : 'DELETE',
			url : GLOBAL.endpoint + 'pet/' + petId
		}).then(successFn, errorFn);
	};
		
	service.findPetById = function(petId, successFn, errorFn)  {
		return $http({
			method : 'GET',
			url : GLOBAL.endpoint + 'pet/' + petId
		}).then(successFn, errorFn);
	};
	
	service.getAll = function(successFn, errorFn)  {
		return $http({
			method : 'GET',
			url : GLOBAL.endpoint + 'pet/all'
		}).then(successFn, errorFn);
	};
	
	return service;
});
