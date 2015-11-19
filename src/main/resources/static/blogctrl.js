var app = angular.module("app",[]);

app.controller("blogCtrl", function($scope,$log,$http) {
    $scope.entry = {title : "Title",
                    content : "Content"};
    $scope.entries = [];
    $log.debug('se creo el $scope');

    $scope.loadData = function() {
        var configList = {
                method: "GET",
                url: "blogs"
                    };

        var response=$http(configList);

        response.success(function(data, status, headers, config) {
            $scope.entries = data;
            });

        response.error(function(data, status, headers, config) {
            alert("Ha fallado la petición. Estado HTTP:"+status);
            });
    };
    
      $scope.processForm = function() {
        $log.debug($scope.entry);
        $http({
            method  : 'POST',
            url     : 'blog',
            data    : $scope.entry
        }).success(function(data) {
            console.log(data);
            $scope.loadData();
        });
    };
    
    
    $scope.delete = function(idx){
        $log.debug ($scope.entry);
        $http({
            method: 'POST',
            url:  'del',
            data: idx  
        }).success(function(data){
           console.log(data);
           $scope.loadData(); 
        });       
    };
    
    $scope.edit = function(idx){
        $scope.delete(idx);
        $scope.processForm();  
    };
    
    
    $scope.loadData();
});