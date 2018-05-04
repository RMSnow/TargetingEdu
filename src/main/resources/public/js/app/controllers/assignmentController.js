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
        $state.go("index.checkhomework");

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

    var getStudentList = function () {
        $http({
            url: "teacher/studentList",
            method: "get",
        }).success(function (response) {
            $scope.students = response;
        })
    }
    getStudentList();

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
        $state.go("index.checkhomework");

    }
    $scope.getQuestions = function (assignmentUid) {
        localStorage.setItem("Uid", assignmentUid);

        $state.go("index.questionList");
    }
}
function QuestionCtrl($scope, $http, $state) {
    var Uid = localStorage.getItem("Uid");

    $http({
        url: "assignment/questions",
        method: "get",
        params: {
            assignmentUid: Uid
        }
    }).success(function (response) {
        if (response['code'] == 200) {
            $scope.questions = response['content'];

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


        $state.go("index.check");
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
    }


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
    })


    getTypeList(submitHomeworkUid);


    //提交批改
    $scope.info = {};
    $scope.info.uid = submitHomeworkUid;

    $scope.scoreCentesimal = 0;
    var updateScore = function () {
        score = calScore();
        localStorage.setItem("score", score);
        $scope.scoreCentesimal = score;
    }
    var calScore = function () {
        score = 0;
        for (j = 0, len = checks.length; j < len; j++) {
            if (checks[j] == 1) score = score + 1;
        }
        return score;
    };
    $scope.getScoreCentesimal = function () {
        return localStorage.getItem("score") / checks.length.toFixed(1) * 100;
    };


    $scope.save = function () {
        $scope.info.checkResult = localStorage.getItem("checks");
        $scope.info.score = calScore();

        $http({

            url: 'submitHomework/check',
            method: 'POST',
            data: $scope.info
        }).success(function (x) {
            localStorage.removeItem("checks");


            //保存成功后更新数据
            alert("提交成功!");
            $state.go('index.checkhomework', {uid: submitHomeworkUid});
        });
    }

    var checks = new Array();

    $scope.right = function (order) {
        checks[order - 1] = 1;

        localStorage.setItem("checks", JSON.stringify(checks));
        updateScore();
        console.log(JSON.parse(localStorage.getItem("checks")));

    }
    $scope.wrong = function (order) {
        checks[order - 1] = 0;

        updateScore();
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
                $state.go('index.assignment');
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
                    $state.go('index.assignment');

                }
            })
        });
    };
}

function AssignmentModelCtrl($scope, $modalInstance) {
    $scope.assignment = {};
    $scope.datepicker = function () {
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
