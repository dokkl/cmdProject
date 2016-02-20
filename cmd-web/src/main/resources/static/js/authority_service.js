'use strict';

App.factory('AuthorityService', ['$http', '$q', function($http, $q){

    return {

        fetchAllAuthorities: function() {
            return $http.get('http://localhost:8080/admin/rest/authority/')
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

        createAuthority: function(authority){
            return $http(
                {
                    method: 'POST',
                    url : 'http://localhost:8080/admin/rest/authority/',
                    data : authority,
                    headers: {'Content-Type':'application/json'}
                }
            )
            //return $http.post('http://localhost:8080/admin/rest/authority/', authority)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while creating authority : ' + authority.authority);
                    return $q.reject(errResponse);
                }
            );
        },

        updateAuthority: function(authority, id){
            return $http.put('http://localhost:8080/admin/rest/authority/'+id, authority)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while updating authority');
                    return $q.reject(errResponse);
                }
            );
        },

        deleteAuthority: function(id){
            return $http.delete('http://localhost:8080/admin/rest/authority/'+id)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while deleting authority');
                    return $q.reject(errResponse);
                }
            );
        }

    };

}]);