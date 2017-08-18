angular.module('vitrine').controller('loginController',['$scope',function($scope){
        $scope.loginUser= function(form){
            if(form.$valid){
               alert('Bienvenue'+'$scope.username'+'!') 
            }
            else{
               //..... 
            }
            
            
        };
}]);