var app = angular

    .module('myApp', ['ui.router', 'ui.bootstrap', 'ngAnimate'])

    .controller('myCtrl', function ($scope,$http) {
        $http({
            header: {"authorization": localStorage.getItem("token")},
            url: "teacher/getTeacherDetail",
            method: "get",
        }).success(function (response) {
            $scope.teacher = response;
        })


    })
    .config(['$httpProvider', function ($httpProvider) {
    //token请求头配置
        var token  = localStorage.getItem('token');
        if (token !=null){
            $httpProvider.defaults.headers.common['Authorization'] =token ;
        }

    $httpProvider.interceptors.push('responseObserver');

}]);

//统一的处理函数
app.factory('responseObserver', function responseObserver($q, $window) {
    return {
        'responseError': function (errorResponse) {
            switch (errorResponse.code) {
                case 403:
                    // alert(errorResponse.toString());
                    // $window.location = './403.html';
                    break;
                case 404:
                    // alert(errorResponse.toString());
                    // $window.location = './403.html';
                    break;
                case 500:
                    // alert(errorResponse.toString());
                    break;
            }
            return  (errorResponse);
        }
    };
})

// AngularJS 解析HTML安全策略
    .filter('trustHtml', function ($sce) {
        return function (input) {
            return $sce.trustAsHtml(input);
        }
    })

;

// 页面间传值
app.factory('Data', function () {
    var savedData = {}

    function set(data) {
        savedData = data;
    }

    function get() {
        return savedData;
    }

    return {
        set: set,
        get: get
    }
});

app.factory('HttpInterceptor', function ($q, $injector) {
    return {
        request: function (config) {
            return config;
        },
        requestError: function (err) {
            return  (err);
        },
        response: function (res) {
            return res;
        },
        responseError: function (err) {
            var stateService = $injector.get('$state');
            if (-1 === err.status) {
                // 远程服务器无响应
            } else if (500 === err.status) {
                // alert(err.toString())
            } else if (404 === err.status) {
                // alert(err.toString())
            }
            // return $q.reject(err);
            return err;
        }
    };
});
// app.factory('HttpInterceptor', function ($q, $injector) {
//     return {
//         request: function (config) {
//             return config;
//         },
//         requestError: function (err) {
//             return $q.reject(err);
//         },
//         response: function (res) {
//             return res;
//         },
//         responseError: function (err) {
//             var stateService = $injector.get('$state');
//             if (-1 === err.status) {
//                 // 远程服务器无响应
//             } else if (500 === err.status) {
//                 // alert(err.toString())
//             } else if (404 === err.status) {
//                 // alert(err.toString())
//             }
//             // return $q.reject(err);
//             return err;
//         }
//     };
// });
// 路由转换
app.config(function ($stateProvider, $urlRouterProvider, $locationProvider,
                     $httpProvider) {
    $httpProvider.interceptors.push('HttpInterceptor');
    $urlRouterProvider.otherwise('/login');
    $stateProvider.state('index', {
        url: '/index',
        views: {
            '': {
                templateUrl: 'partials/home.html',
                controller: ClassListCtrl
            }
        }
    }).state('class', {
        url: '/class',
        views: {
            '': {
                templateUrl: 'partials/class.html',
                controller: StudentCtrl
            }

        }
    }).state('check', {
        url: '/check',
        params: {
            'uid': null,
        },
        views: {
            '': {
                templateUrl: 'partials/check.html',
                controller: CheckCtrl
            }

        }
    }).state('feedback', {
        url: '/feedback',
        views: {
            '': {
                templateUrl: 'partials/feedback.html',
                controller: ClassListCtrl
            }
        }
    }).state('studentAnalysis', {
        url: '/studentAnalysis',
        views: {
            '': {
                templateUrl: 'partials/studentAnalysis.html',
                controller: ClassListCtrl
            }
        }
    }).state('prepareLesson', {
        url: '/prepareLesson',
        views: {
            '': {
                templateUrl: 'partials/prepareLesson.html',
                controller: PrepareLessonCtrl

            }
        }
    }).state('assignment', {
        url: '/assignment',
        views: {
            '': {
                templateUrl: 'partials/assignment.html',
                controller: AssignmentCtrl
            }
        }
    }).state('feedbackson', {
        url: '/feedbackson',
        views: {
            '': {
                templateUrl: 'partials/feedbackson.html',
                controller: ClassListCtrl
            }
        }
    }).state('addHomework', {
        url: '/addHomework',
        views: {
            '': {
                templateUrl: 'partials/addHomework.html',
                controller: AddHomeworkCtrl
            }
        }
    }).state('afterclassHomework', {
        url: '/afterclassHomework',
        views: {
            '': {
                templateUrl: 'partials/afterclassHomework.html',
                controller: ClassListCtrl
            }
        }
    }).state('addClass', {
        url: '/addClass',
        views: {
            '': {
                templateUrl: 'partials/addClass.html',
                controller: ClassListCtrl
            }
        }
    }).state('addStudent', {
        url: '/addStudent',
        views: {
            '': {
                templateUrl: 'partials/addStudent.html',
                controller: AddStudentCtrl
            }
        }
    }).state('login', {
        url: '/login',
        views: {
            '': {
                templateUrl: 'partials/login.html',
                controller: LoginCtrl
            }
        }
    }).state('register', {
        url: '/register',
        views: {
            '': {
                templateUrl: 'partials/register.html',
                controller: RegisterCtrl
            }
        }
    }).state('addHomework_2', {
        url: '/addHomework_2',
        views: {
            '': {
                templateUrl: 'partials/addHomework_2.html',
                controller: ClassListCtrl
            }
        }
    }).state('detailStudent', {
        url: '/detailStudent',
        views: {
            '': {
                templateUrl: 'partials/detailStudent.html',
                controller: StudentDetailCtrl
            }
        }
    }).state('detailClass', {
        url: '/detailClass',
        views: {
            '': {
                templateUrl: 'partials/detailClass.html',
                controller: DetailClassCtrl
            }
        }
    }).state('studentAnalysisson', {
        url: '/studentAnalysisson',
        views: {
            '': {
                templateUrl: 'partials/studentAnalysisson.html',
                controller: StudentCtrl
            }
        }
    }).state('upload', {
        url: '/upload',
        views: {
            '': {
                templateUrl: 'upload/index.html',
            }
        }
    }).state('checkhomework', {
        url: '/checkhomework',
        params: {
            'uid': null,
        },
        views: {
            '': {
                templateUrl: 'partials/checkhomework.html',
                controller: CheckHomeworkCtrl
            }
        }
    }).state('homeworkPreview', {
        url: '/homeworkPreview',
        views: {
            '': {
                templateUrl: 'partials/homeworkPreview.html',
                controller: HomeworkPreviewCtrl
            }
        }
    }).state('homeworkPreview2', {
        url: '/homeworkPreview2',
        views: {
            '': {
                templateUrl: 'partials/homeworkPreview2.html',
                controller: AssignmentCtrl
            }
        }
    }).state('record', {
        url: '/record',
        views: {
            '': {
                templateUrl: 'partials/record.html',
                controller: RecordCtrl
            }
        }
    }).state('questionList',{
        url:'/questionList',
        views:{
            '':{
                templateUrl:'partials/questionList.html',
                controller:QuestionCtrl
            }
        }
    })


});
app.filter('Enabled', function () {
    return function (enabled) {
        if (enabled) {
            return '可用';
        } else {
            return '禁用';
        }
    };
});
