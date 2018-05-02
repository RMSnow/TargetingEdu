function LoginCtrl($scope, $http, $state) {
    // $scope.teacher = [];

    $scope.teacher = null;

    $scope.login = function () {

        $http({

            url: 'user/teacher/login',
            method: 'POST',
            params: $scope.teacher

        }).success(function (response) {

            console.log("response: " + response);

            var message = response["message"]

            var content = response["content"]

            if (message == "success") {

                localStorage.setItem("token", "Bearer " + content)
                console.log("whole token： " + localStorage.getItem("token"))


                $state.go('index');
            }

        }).error(function (response) {
            alert('error')
        })


    }

}

function RegisterCtrl($scope, $http) {
    // $scope.wrong = false;
    $scope.teacher = null;

    $scope.register = function () {

        $http({

            url: '/user/teacher/register',
            method: 'post',
            params: $scope.teacher
        }).success(function (response) {

            if (response['code'] == 200) {

                $state.go('login');
            } else {

                alert(response['content']);

            }
        })
    }
}