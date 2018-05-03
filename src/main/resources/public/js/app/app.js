var app = angular

    .module('myApp', ['ui.router', 'ui.bootstrap', 'ngAnimate','ui.calendar'])

    .controller('myCtrl', function ($scope,$http,$state) {
        $http({
            header: {"authorization": localStorage.getItem("token")},
            url: "teacher/getTeacherDetail",
            method: "get",
        }).success(function (response) {
            $scope.teacher = response;
        })
        $scope.state = $state;

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
// 路由转换
app.config(function ($stateProvider, $urlRouterProvider, $locationProvider,
                     $httpProvider) {
    $httpProvider.interceptors.push('HttpInterceptor');
    $urlRouterProvider.otherwise('/login');
    $stateProvider.state('index', {
        url: '/index',
        cache:false,
        views: {
            '': {
                templateUrl: 'partials/home.html',
            },
            'navbar-header@index':{
                templateUrl:'partials/main/navbar-header.html',
                controller:ClassListCtrl
            },
            'navbar-aside@index':{
                templateUrl:'partials/main/navbar-aside.html',
            },
            'content@index':{
                templateUrl:'partials/main.html',
                controller:ClassListCtrl
            },
        }
    }).state('test', {
        url: '/test',
        views: {
            '': {
                templateUrl: 'partials/test.html',
            }

        }
    }).state('index.classlist', {
        url: '/classlist',
        views: {
            'content@index': {
                templateUrl: 'partials/classlist.html',
                controller: ClassListCtrl
            }

        }
    }).state('index.class', {
        url: '/class',
        views: {
            'content@index': {
                templateUrl: 'partials/class.html',
                controller: StudentCtrl
            }

        }
    }).state('index.check', {
        url: '/check',
        params: {
            'uid': null,
        },
        views: {
            'content@index': {
                templateUrl: 'partials/check.html',
                controller: CheckCtrl
            }

        }
    }).state('index.studentAnalysis', {
        url: '/studentAnalysis',
        views: {
            'content@index': {
                templateUrl: 'partials/studentAnalysis.html',
                controller: ClassListCtrl
            }
        }
    }).state('index.prepareLesson', {
        url: '/prepareLesson',
        views: {
            'content@index': {
                templateUrl: 'partials/prepareLesson.html',
                controller: PrepareLessonCtrl

            }
        }
    }).state('index.assignment', {
        url: '/assignment',
        views: {
            'content@index': {
                templateUrl: 'partials/assignment.html',
                controller: AssignmentCtrl
            }
        }
    }).state('index.feedbackson', {
        url: '/feedbackson',
        views: {
            'content@index': {
                templateUrl: 'partials/feedbackson.html',
                controller: ClassListCtrl
            }
        }
    }).state('index.addHomework', {
        url: '/addHomework',
        views: {
            'content@index': {
                templateUrl: 'partials/addHomework.html',
                controller: AddHomeworkCtrl
            }
        }
    }).state('index.afterclassHomework', {
        url: '/afterclassHomework',
        views: {
            'content@index': {
                templateUrl: 'partials/afterclassHomework.html',
                controller: ClassListCtrl
            }
        }
    }).state('index.addClass', {
        url: '/addClass',
        views: {
            'content@index': {
                templateUrl: 'partials/addClass.html',
                controller: ClassListCtrl
            }
        }
    }).state('index.addStudent', {
        url: '/addStudent',
        views: {
            'content@index': {
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
    }).state('index.addHomework_2', {
        url: '/addHomework_2',
        views: {
            'content@index': {
                templateUrl: 'partials/addHomework_2.html',
                controller: ClassListCtrl
            }
        }
    }).state('index.detailStudent', {
        url: '/detailStudent',
        views: {
            'content@index': {
                templateUrl: 'partials/detailStudent.html',
                controller: StudentDetailCtrl
            }
        }
    }).state('index.detailClass', {
        url: '/detailClass',
        views: {
            'content@index': {
                templateUrl: 'partials/detailClass.html',
                controller: DetailClassCtrl
            }
        }
    }).state('index.studentAnalysisson', {
        url: '/studentAnalysisson',
        views: {
            'content@index': {
                templateUrl: 'partials/studentAnalysisson.html',
                controller: StudentCtrl
            }
        }
    }).state('index.upload', {
        url: '/upload',
        views: {
            'content@index': {
                templateUrl: 'upload/index.html',
            }
        }
    }).state('index.checkhomework', {
        url: '/checkhomework',
        params: {
            'uid': null,
        },
        views: {
            'content@index': {
                templateUrl: 'partials/checkhomework.html',
                controller: CheckHomeworkCtrl
            }
        }
    }).state('index.homeworkPreview', {
        url: '/homeworkPreview',
        views: {
            'content@index': {
                templateUrl: 'partials/homeworkPreview.html',
                controller: HomeworkPreviewCtrl
            }
        }
    }).state('index.homeworkPreview2', {
        url: '/homeworkPreview2',
        views: {
            'content@index': {
                templateUrl: 'partials/homeworkPreview2.html',
                controller: AssignmentCtrl
            }
        }
    }).state('index.questionList',{
        url:'/questionList',
        views:{
            'content@index':{
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