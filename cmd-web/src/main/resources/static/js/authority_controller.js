'use strict';

App.controller('AppController2', ['$scope', 'AuthorityService', function($scope, AuthorityService) {
    var self = this;
    self.authority = {id:null, authority:''};
    self.authorities = [];

    self.fetchAllAuthorities = function(){
        AuthorityService.fetchAllAuthorities()
            .then(
            function(d) {
                self.authorities = d;
            },
            function(errResponse){
                console.error('Error while fetching Currencies');
            }
        );
    };

    self.createAuthority = function(authority){
        AuthorityService.createAuthority(authority)
            .then(
            self.fetchAllAuthorities,
            function(errResponse){
                console.error('Error while creating Authority.');
            }
        );
    };

    self.updateAuthority = function(user, id){
        AuthorityService.updateAuthority(user, id)
            .then(
            self.fetchAllAuthorities,
            function(errResponse){
                console.error('Error while updating Authority.');
            }
        );
    };

    self.deleteAuthority = function(id){
        AuthorityService.deleteAuthority(id)
            .then(
            self.fetchAllAuthorities,
            function(errResponse){
                console.error('Error while deleting Authority.');
            }
        );
    };

    self.fetchAllAuthorities();

    self.submit = function() {
        if(self.authority.id===null){
            console.log('Saving New Authority', self.authority);
            self.createAuthority(self.authority);
        }else{
            self.updateAuthority(self.authority, self.authority.id);
            console.log('Authority updated with id ', self.authority.id);
        }
        self.reset();
    };

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.authorities.length; i++){
            if(self.authorities[i].id === id) {
                self.authority = angular.copy(self.authorities[i]);
                break;
            }
        }
    }

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.authority.id === id) {//clean the form if the user to be deleted is shown there.
            self.reset();
        }
        self.deleteAuthority(id);
    }

    self.reset = function(){
        self.authority={id:null,authority:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);