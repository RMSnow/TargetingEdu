function ClassListCtrl($scope, $http, $state) {
    $scope.currentPage = 1;
    $scope.numPerPage = 10;
    $scope.maxSize = 5;
    var getClassList = function () {
        $http({
            url: "teacher/findPageClass",
            method: "get",
            params: {
                currentPage: $scope.currentPage - 1,
                numPerPage: $scope.numPerPage,
            }
        }).success(function (response) {
            $scope.TotalItems = response.totalElements;
            $scope.classes = response.content;
        })
        $scope.pageChanged = function () {
            $http({
                url: "teacher/findPageClass",
                method: "get",
                params: {
                    currentPage: $scope.currentPage - 1,
                    numPerPage: $scope.numPerPage,
                }
            }).success(function (response) {
                $scope.TotalItems = response.totalElements;
                $scope.classes = response.content;
            })
        }
    }
    getClassList();

    $scope.data = null;
    $scope.rows = [];
    $scope.teacher = null;


    //添加班级
    $scope.save = function () {
        $http({

            url: 'teacher/addClass',
            method: 'POST',
            params: $scope.data,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        }).success(function (x) {
            //保存成功后更新数据
            // $scope.get(x.id);
            // $('.close').click();
            alert("添加成功!");
            $state.go('index');
        });
    }


    //编辑
    $scope.edit = function (id) {
        for (var i in $scope.rows) {
            var row = $scope.rows[i];
            if (id == row.id) {
                $scope.data = row;
                return;
            }
        }
    }

    $scope.deleteClass = function (classid) {
        $http({
            headers: {"Authorization": localStorage.getItem("token")},
            url: 'teacher/deleteClass',
            method: 'delete',
            params: {
                classid: classid
            }
        }).success(function (response) {

            getClassList();
        });
    }
    //查看详情页面
    $scope.viewDetail = function (number) {
        localStorage.setItem("classUid", number);

        console.log(localStorage.getItem("classUid"));
        $state.go('detailClass')
    }

    $scope.checkAll = function (checked) {
        angular.forEach($scope.rows, function (x) {
            x.$checked = checked;
        });
    };

    $scope.selected = [];


    $scope.getClassByStatus = function (id) {
        $scope.id = id;
        $http({
            url: 'dept/add',
            method: 'post',
            params: {
                id: $scope.id
            }
        }).success(function (response) {
            $state.go('index.organzationManage', location.reload('index.organzationManage'));
        })
    }
    $scope.find = function () {
        $state.go('feedbackson');
        //alert("hsudhsaudhsau");
    }

    $scope.retreat = function () {
        //$('.close').click();
        $state.go('feedback');
        //alert("hsudhsaudhsau");
    }
    $scope.addHomework = function () {
        //$('.close').click();
        $state.go('addHomework');
        //alert("hsudhsaudhsau");
    }

    $scope.checkAll = function (checked) {
        angular.forEach($scope.rows, function (x) {
            x.$checked = checked;
        });
    };

    $scope.findAllByStatus = function (x) {
        $http(
            {
                url: 'class/findAllByStatus',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: 'status=' + x
            }).success(function (rows) {
            $scope.rows = rows;
        });
    };

    $scope.findAll = function () {
        $http(
            {

                url: 'teacher/classList',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).success(function (rows) {
            $scope.rows = rows;
        });
    };

}


function StudentCtrl($scope, $http, $state) {

    var getClassList = function () {
        $http({
            url: "teacher/classList",
            method: "get",
        }).success(function (response) {
            if (response['code'] == 200) {
                $scope.classes = response['content'];

            }
        })
    }
    getClassList();


    $scope.findClass = function (id) {


        var divs = document.getElementById("container").getElementsByTagName("ul");

        var len = divs.length;

        $http({
            url: "class/findStudentsByClassNumber",
            method: 'get',
            params: {
                number: id
            }
        }).success(function (rows) {


            sessionStorage.setItem("class_id", id);


            // alert(sessionStorage.getItem("class_id"));

            $scope.rows = rows;

            $(".box box-primary").attr('id', id);

        });

    }

    $scope.getStudents = function (id) {

        localStorage.setItem("class_id",id);

        $scope.currentPage = 1;//当前页
        $scope.numPerPage = 10;//每页显示1条数据
        $scope.maxSize = 5;
        $http({
            url: "class/findPageStudentsByClassNumber",
            method: 'get',
            params: {
                currentPage: $scope.currentPage-1,
                numPerPage: $scope.numPerPage,
                stuclass: id
            }
        }).success(function (response) {
            sessionStorage.setItem("class_id", id);
            $scope.TotalItems = response.totalElements;
            $scope.items = response.content;
            $(".box box-primary").attr('id', id);
        })

        $scope.pageChanged = function () {
            var id = sessionStorage.getItem("class_id");
            $http({
                url: "class/findPageStudentsByClassNumber",
                method: 'get',
                params: {
                    currentPage: $scope.currentPage - 1,
                    numPerPage: $scope.numPerPage,
                    stuclass: id
                }
            }).success(function (response) {
                $scope.TotalItems = response.totalElements;
                $scope.items = response.content;
            }).error(function (response) {
                $scope.error = "An error has occured!"
                    + response.ExceptionMessage;
            })

        }

    };
    $scope.deleteStudent = function (studentUid) {
        var id = sessionStorage.getItem("class_id");
        $http({
            url: "class/deletestudent",
            method: 'delete',
            params: {
                currentPage: $scope.currentPage - 1,
                numPerPage: $scope.numPerPage,
                studentUid: studentUid,
                classid: id
            }
        }).success(function (response) {
            $scope.TotalItems = response.totalElements;
            $scope.items = response.content;
        });
    };

    $scope.checkAll = function (checked) {
        angular.forEach($scope.rows, function (x) {
            x.$checked = checked;
        });
    };
    // $scope.data = {}; not null
    $scope.rows = [];

    //添加
    $scope.add = function () {
        $scope.data = {
            student_name: '',
            student_no: '',
            start_time: '',
            comment: '',
            parent_tel: '',
            parent_wechat: ''
        };
    }

    //保存
    $scope.save = function () {
        $http({
            url: 'student/save',
            method: 'POST',
            data: $scope.data
        }).success(function (r) {
            //保存成功后更新数据
            $scope.get(r.id);
            $('.close').click();
            alert("添加成功!");
            $state.go('class');
        });
    }

    // $scope.student = null;

    $scope.getDetails = function (studentUid) {
        localStorage.setItem("studentUid", studentUid);
        $state.go("detailStudent");
    }


}

function StudentDetailCtrl($scope, $http, $state) {
    var studentUid = localStorage.getItem("studentUid");
    $http({
        url: 'student/findByStudentUid',
        method: 'get',
        params: {studentUid: studentUid}
    }).success(function (response) {
        $scope.student = response;
    })
}


function AddStudentCtrl($scope, $http, $state) {
    $scope.addStudent = function () {

        var class_id = sessionStorage.getItem("class_id");

        console.log(class_id)


        $http({
            url: 'teacher/addStudent',
            method: 'post',

            params: {
                classUid: class_id,
                studentNumber: $scope.studentNumber
            }

        }).success(function (response) {
            var message = response['message'];

            if (message == 'success') {
                alert("添加成功!");

                $state.go('class');
            } else {
                alert(response['content']);

            }

        })


    }

}