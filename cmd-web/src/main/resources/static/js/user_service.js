'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

    return {

        fetchAllUsers: function() {
            //http://localhost:8080/admin/rest/user/
            return $http.get('/admin/rest/user/')
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

        createUser: function(user){
            return $http(
                {
                    method: 'POST',
                    url : '/admin/rest/user/',
                    data : user,
                    headers: {'Content-Type':'application/json'}
                }
            ) //post('http://localhost:8080/admin/rest/authority/', authority)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while creating user : ' + user.username);
                    return $q.reject(errResponse);
                }
            );
        },

        updateUser: function(user, id){
            return $http.put('/admin/rest/user/'+id, user)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while updating user');
                    return $q.reject(errResponse);
                }
            );
        },

        deleteUser: function(id){
            return $http.delete('/admin/rest/user/'+id)
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while deleting user');
                    return $q.reject(errResponse);
                }
            );
        }

    };

}]);