angular.module(PKG.name + '.feature.services')
  .controller('ServicesDetailController', function($scope, MyDataSource, $state, myRuntimeService, myProgramPreferencesService) {
    var dataSrc = new MyDataSource($scope),
        path = '/apps/' +
          $state.params.appId + '/services/' +
          $state.params.programId;

    $scope.start = function() {
      $scope.status = 'STARTING';

      var requestObj = {
        _cdapNsPath: path + '/start',
        method: 'POST'
      };

      if ($scope.runtimeArgs && Object.keys($scope.runtimeArgs).length > 0) {
        requestObj.body = $scope.runtimeArgs;
      }

      dataSrc.request(requestObj);
    };

    $scope.stop = function() {
      $scope.status = 'STOPPING';
      dataSrc.request({
        _cdapNsPath: path + '/stop',
        method: 'POST'
      });
    };

    dataSrc.poll({
      _cdapNsPath: path + '/status'
    }, function(res) {
      $scope.status = res.status;
    });

    $scope.openPreferences = function() {
      myProgramPreferencesService.show('services');
    };

    $scope.openRuntime = function() {
      myRuntimeService.show().result.then(function(res) {
        $scope.runtimeArgs = res;
      });
    };

  });
