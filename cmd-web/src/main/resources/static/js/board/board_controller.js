/**
 * Created by Coupang on 2015-12-08.
 */
'use strict';

App.controller('AppController', ['$scope', 'BoardService', function($scope, BoardService) {
	var self = this;

	self.boards=[];

	self.main = {
		page: 1,
		size: 10,
		totalPages: 10,
		firstPage: true,
		lastPage: false,
		totalElements: 10,
	};



	self.pageBtnArray =[];
	self.btnArrayDefaultCnt = 5;
	self.btnChunkIndex = 0;
	self.startPageBtnNumber = 1;
	self.currentChunkNumber = 1;
	self.btnChunkCnt = 0;

	self.settingPageCtrl = function(totalPages, currentPage) {

		self.btnChunkCnt = parseInt(totalPages / self.btnArrayDefaultCnt) + 1;   //버튼덩어리 수
		var lastChunkBtnCnt = totalPages % self.btnArrayDefaultCnt; //마지막 chunk 의 버튼갯수

		self.startPageBtnNumber = self.btnChunkIndex * self.btnArrayDefaultCnt + 1; //페이지버튼의 시작넘버

		self.pageBtnArray = new Array(); //버튼배열 초기화
		if (self.btnChunkCnt == self.currentChunkNumber) { //마지막 btn chunk 이면
			for (var i = 0; i < lastChunkBtnCnt; i++) {
				self.pageBtnArray[i] = self.startPageBtnNumber + i;
			}
		} else {
			for (var i = 0; i < self.btnArrayDefaultCnt; i++) {
				self.pageBtnArray[i] = self.startPageBtnNumber + i;
			}
		}
	}

	self.newNextPage = function() {
		if (self.currentChunkNumber == self.btnChunkCnt) {
			return;
		}
		self.main.page = self.startPageBtnNumber + self.btnArrayDefaultCnt; //다음화면의 페이지버튼의 시작넘버
		self.btnChunkIndex++;
		self.currentChunkNumber++;
		//alert(self.main.page);
		self.fetchBoards();

	};

	self.newPreviousPage = function() {
		if (self.currentChunkNumber == 1) {
			return;
		}
		self.main.page = self.startPageBtnNumber - self.btnArrayDefaultCnt; //이전화면의 페이지버튼의 시작넘버
		self.btnChunkIndex--;
		self.currentChunkNumber--;
		//alert(self.main.page);
		self.fetchBoards();

	};

	self.searchParam = {
		dateBegin: '',
		dateEnd: '',
		shippingCompanyCode:''
	}

	self.mainInit = function() {
		self.main.page = 1;
		self.main.size = 10;
		self.main.totalPages =10;
		self.main.firstPage = true;
		self.main.lastPage = false;
		self.main.totalElements = 10;
	}
	self.search = function() {
		self.mainInit(); //페이징관련 초기화
		var $datePicker = $("#order-datepicker");
		var datepickerData = $datePicker.data('daterangepicker');
		self.searchParam.dateBegin = datepickerData.startDate.format("YYYY-MM-DD");
		self.searchParam.dateEnd = datepickerData.endDate.format("YYYY-MM-DD");

		BoardService.searchBoards(self.main, self.searchParam)
			.then(
			function(d) {
				self.boards = d.content;
				self.main.page = d.number + 1;
				self.main.size = d.size;
				self.main.totalPages = d.totalPages;
				self.main.firstPage = d.firstPage;
				self.main.lastPage = d.lastPage;
				self.main.totalElements = d.totalElements;
			},
			function(errResponse){
				console.error('Error while fetching Currencies');
			}
		);
	}

	self.fetchBoards = function(){
		BoardService.fetchBoards(self.main)
			.then(
			function(d) {
				self.boards = d.content;
				self.main.page = d.number + 1;
				self.main.size = d.size;
				self.main.totalPages = d.totalPages;
				self.main.firstPage = d.firstPage;
				self.main.lastPage = d.lastPage;
				self.main.totalElements = d.totalElements;

				self.settingPageCtrl(d.totalPages, d.number + 1);
			},
			function(errResponse){
				console.error('Error while fetching Currencies');
			}
		);
	};

	self.fetchBoards();
	//self.search();

	self.nextPage = function() {
		if (self.main.page < self.main.totalPages) {
			self.main.page++;
			self.fetchBoards();
		}
	};

	self.previousPage = function() {
		if (self.main.page > 1) {
			self.main.page--;
			self.fetchBoards();
		}
	};

	self.movePage = function(currentPage) {
		self.main.page = currentPage;
		self.fetchBoards();
	};


}]);
