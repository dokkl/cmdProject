/**
 * Created by Coupang on 2015-12-08.
 */
'use strict';

App.factory('BoardService', ['$http', '$q', function($http, $q){

	return {

		fetchBoards: function(main) {
			return $http.get('/rest/notice?page=' + main.page + '&size=' + main.size)
				.then(
				function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while fetching users');
					return $q.reject(errResponse);
				}
			);
		},

		/*searchBoards: function(main, param) {
			return $http.get('/api/v1/stats/searchRelease?page=' + main.page + '&size=' + main.size
					+ '&startDate=' + param.dateBegin + '&endDate=' + param.dateEnd + '&shippingCompanyCode=' + param.shippingCompanyCode)
				.then(
				function(response){
					return response.data;
				},
				function(errResponse){
					console.error('Error while fetching users');
					return $q.reject(errResponse);
				}
			);
		},*/

	};

}]);


