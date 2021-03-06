/*global define*/

define([
	'jquery',
	'bootstrap',
	'underscore',
	'backbone',
	'templates',
	'handlebars',
	'../collections/ctaFavorites',
	'./route',
	'../models/ctaRoute',
	'layoutmanager'
], function ($, Bootstrap, _, Backbone, JST, Handlebars, CtaFavoritesCollection, RouteView, CtaRoute) {
	'use strict';
	
	var FavoriteView = Backbone.Layout.extend({
		template: JST['app/scripts/templates/favorite.hbs'],
		initialize: function(){
			var self = this;
			self.favoritesCollection = CtaFavoritesCollection;
			self.favoritesCollection.fetch({
				success: function(data){
					self.render();
				},
				error: function(collection, response, options){

				}
			});
			self.favoritesCollection.on("change reset add remove", function() {
		        self.render();
		    });
		},
		removeFavorite: function(serviceId){
			if(typeof(Storage)!=="undefined"){
				var favorite = this.favoritesCollection.findWhere({
					serviceId: serviceId
				});
				favorite.destroy();
				this.favoritesCollection.remove(favorite);
				this.render();
				RouteView.render();
			}
		},
		beforeRender: function(){
			$(".favorites").empty().append(this.el);
		},
		afterRender: function(){
			var self = this;
			this.$(".trash").click(function(event){
				self.removeFavorite(event.currentTarget.id);
			});

			this.$(".favoriteRoute").click(function(event){
				var serviceId = event.currentTarget.classList[1];
				var ctaRoute = new CtaRoute();
				ctaRoute.url = 'stationId?serviceId=' + serviceId;
				ctaRoute.fetch({
					success: function(data){
						var routeData = data.toJSON();
						routeData.route = routeData.route[0];
						routeData.routeIcon = routeData.type === 'rail' ?  'images/cta_train.svg' : 'images/cta_bus.svg';
						RouteView.setRoute(routeData);
					},
					error: function(collection, response, options){
						
					}
				});
			});
		},
		serialize: function(){
			return {
				favorites: this.favoritesCollection.toJSON()
			}
		}
	});

	return FavoriteView;
});