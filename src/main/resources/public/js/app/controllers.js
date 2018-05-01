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

function DetailClassCtrl($scope, $http) {
    var classUid = localStorage.getItem("classUid");

    console.log("classUid" + classUid)
    $http({
        url: 'class/findByNumber',
        method: 'get',
        params: {
            class_id: classUid
        }
    }).success(function (response) {
        $scope.class = response;
    })
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

function AddHomeworkCtrl($scope, $http) {

    var getPage = function () {

        $scope.data = {};
        $scope.data.difficulty = $scope.difficulty;
        $scope.data.type = $scope.type;
        var page = 1;
        var pageSize = 5;


        $http({

            url: 'question/getAllQuestions',
            method: 'get',
        }).success(function (response) {

            $scope.questions = response;
        })


    };

    var search = function () {

        $http({

            url: 'question/getAllQuestions',
            method: 'get',
        }).success(function (response) {

            $scope.questions = response;
        })
    }

    search();


    //定义题目id的添加和移除函数
    var selecedQuestions = new Array();
    // $scope.visible = false;
    $scope.add = function (x) {
        x.visible = !x.visible;


        var b = $(event.target).parents('.list-group-item').attr('data-qtype');
        var qid = $(event.target).parents('.list-group-item').attr('data-qid');
        qid = +qid

        selecedQuestions.push(qid);
        localStorage.setItem("selectQuestions", JSON.stringify(selecedQuestions));

        console.log(JSON.parse(localStorage.getItem("selectQuestions")));


    }
    $scope.remove = function (x) {

        x.visible = !x.visible;
        var b = $(event.target).parents('.list-group-item').attr('data-qtype');//获取这一个块里的试题类型
        var qid = $(event.target).parents('.list-group-item').attr('data-qid');
        qid = +qid
        for (var i = 0; i < selecedQuestions.length; i++) {
            if (selecedQuestions[i] == qid) {
                selecedQuestions.splice(i, 1);
            }
        }
        console.log(JSON.parse(localStorage.getItem("selectQuestions")));

        localStorage.setItem("selectQuestions", JSON.stringify(selecedQuestions));

    }

}

function PrepareLessonCtrl($scope, $http, $state) {
    $http({
        url: "teacher/assignmentList",
        method: 'get',
    }).success(function (rows) {
        if (rows['code'] == 200) {
            $scope.assignment = rows;

        }
    });
    $scope.getHomeworks = function (uid) {
        localStorage.setItem("assignmentUid", uid);
        $state.go("checkhomework");

    };
}

function AssignmentCtrl($scope, $http, $state) {
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

    $scope.getHomeworkList = function (class_id) {
        $http({
            url: "class/getHomeworkList",
            method: "get",
            params: {
                classUid: class_id
            }
        }).success(function (response) {
            $scope.hwList = response['content'];
            localStorage.setItem("class_id", class_id);


        })
    }

    $scope.getSubmitHomeworks = function (uid) {
        localStorage.setItem("currentAssignmentUid", uid);
        $state.go("checkhomework");
    }
    $scope.getQuestions=function (assignmentUid) {
        localStorage.setItem("Uid",assignmentUid);

        $state.go("questionList");
    }
}
function QuestionCtrl($scope,$http,$state){
    var Uid =localStorage.getItem("Uid");

    $http({
        url:"assignment/questions",
        method:"get",
        params:{
            assignmentUid:Uid
        }
    }).success(function(response){
        if (response['code'] == 200){
            $scope.questions=response['content'];

        }
    })

}


function CheckHomeworkCtrl($scope, $http, $state, $stateParams) {

    var currentAssignmentUid = localStorage.getItem("currentAssignmentUid");

    $http({
        url: "assignment/findSubmitHomeworkByHomework",
        method: "get",
        params: {
            uid: currentAssignmentUid
        }
    }).success(function (response) {


        $scope.submitList = response;
    });

    $scope.getQuestions = function (uid) {

        localStorage.setItem("submitHomeworkUid", uid);

        $state.go("check");
    }

}


function CheckCtrl($scope, $http, $state, $stateParams) {
    var submitHomeworkUid = localStorage.getItem("submitHomeworkUid");


    var getTypeList = function (uid) {
        $http({
            url: "submitHomework/getTypeList",
            method: "get",
            params: {
                uid: uid
            }
        }).success(function (response) {
            if (response["code"] == 200) {
                $scope.typelist = response['content'];
            }
        })
    };


    $http({
        url: "submitHomework/questions",
        method: "get",
        params: {
            uid: submitHomeworkUid
        }
    }).success(function (response) {
        if (response["code"] == 200) {
            $scope.questionList = response['content'];
        }

    });

    $http({
        url: "submitHomework/getPictures",
        method: "get",
        params: {
            uid: submitHomeworkUid
        }
    }).success(function (response) {

        if (response['message'] == 'success') {
            $scope.picturelist = response['content'];

        }
    });

    $http({
        url: "submitHomework/findByUid",
        method: "get",
        params: {
            uid: submitHomeworkUid
        }
    }).success(function (respense) {

        $scope.info = respense;
    });

    getTypeList(submitHomeworkUid);


    //提交批改
    $scope.info = {};
    $scope.info.uid = submitHomeworkUid;

    $scope.save = function () {
        $scope.info.checkResult = localStorage.getItem("checks");
        score=0;
        checks.forEach(function (v) {
            if (v==1) score=score+1;
        });
        $http({

            url: 'submitHomework/check',
            method: 'post',
            data: $scope.info
        }).success(function (x) {
            localStorage.removeItem("checks");


            //保存成功后更新数据
            alert("提交成功!");
            $state.go('checkhomework', {uid: submitHomeworkUid});
        });
    }

    var checks = new Array();


    $scope.right = function (order) {
        checks[order - 1] = 1;
        localStorage.setItem("checks", JSON.stringify(checks));
        console.log(JSON.parse(localStorage.getItem("checks")));

    };
    $scope.wrong = function (order) {
        checks[order - 1] = 0;

        localStorage.setItem("checks", JSON.stringify(checks));
        console.log(JSON.parse(localStorage.getItem("checks")));

    }
}

function HomeworkPreviewCtrl($scope, $http, $state, $modal) {

    $scope.assignment = {};
    var storedQuestions = JSON.parse(localStorage.getItem("selectQuestions"));

    console.log("pre selectQuestions: " + storedQuestions)

    $http({
        url: "question/findAllByNumberIn",
        method: "get",
        params: {
            array: storedQuestions
        }
    }).success(function (response) {
        $scope.questions = response;

    })
    class_id = localStorage.getItem("class_id")
    $scope.saveHomework = function () {
        $http({
            url: "assignment/addAssignment",
            method: "POST",

            params: $scope.assignment,

            classUid: class_id,
            questions: storedQuestions


        }).success(function (response) {

            console.log("response: " + response);

            var message = response['message'];
            if (message == 'success') {
                $state.go('assignment');
            }


        })
    }

    $scope.showModifyModal = function () {
        var modalInstance = $modal.open({
            templateUrl: 'modal',
            controller: AssignmentModelCtrl,
            scope: $scope
        });


        modalInstance.result.then(function (result) {
            result.classList = class_id;
            result.questionList = storedQuestions;
            $http({
                url: "assignment/teacher/release",
                method: "POST",
                params: result


            }).success(function (response) {

                console.log("response: " + response);

                localStorage.removeItem("selectQuestions");

                if (response['code'] == 200) {

                    alert(response['content'])
                    $state.go('assignment');

                }
            })
        });
    };
}

function AssignmentModelCtrl($scope, $modalInstance) {
    $scope.assignment = {};
    $scope.datepicker=function () {
        $('#datetimepicker').datetimepicker({
            format: 'YYYY/MM/DD',
            locale: moment.locale('zh-cn')
        }).on('dp.change', function (response) {
            var result = new moment(response.date).format('YYYY/MM/DD');
            $scope.assignment.deadline = result;
            console.log($scope.assignment.deadline);
            $scope.$apply();
        });
    }

    $scope.ok = function () {

        $modalInstance.close($scope.assignment);
    };
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}

function RecordCtrl($scope, $http) {

    var addRecord = function () {
        $http({
            url: "employee/add",
            method: 'get',
        }).success(function (rows) {
            // $scope.rows = rows;
        });
    }

    addRecord();

    $http({
        url: "record/findAll",
        method: 'get',
    }).success(function (rows) {
        $scope.rows = rows;
    });

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


