package com.action.miaosha.service;

import com.action.miaosha.dao.GoodsDao;
import com.action.miaosha.domain.Goods;
import com.action.miaosha.domain.MiaoshaGoods;
import com.action.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dell
 * @create 2019-07-18 19:21
 */
@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    //商品列表
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    // 根据goodsId查找goodsVo
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    // 减少库存
    public boolean reduceStock(GoodsVo goods) {
        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setId(goods.getId());
        int count = goodsDao.reduceStock(miaoshaGoods);
        return count > 0;
    }

    public void resetStock(List<GoodsVo> goodsList) {
        for (GoodsVo goodsVo : goodsList) {
            MiaoshaGoods goods = new MiaoshaGoods();
            goods.setGoodsId(goodsVo.getId());
            goods.setStockCount(goodsVo.getStockCount());
            goodsDao.resetStock(goods);
        }
    }
}
