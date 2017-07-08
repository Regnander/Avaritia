package morph.avaritia.integration.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import morph.avaritia.recipe.extreme.ExtremeShapedRecipe;
import net.minecraft.item.ItemStack;

/**
 * Created by brandon3055 on 18/02/2017.
 */
public class ExtremeShapedRecipeHandler implements IRecipeHandler<ExtremeShapedRecipe> {

	@Override
	public Class<ExtremeShapedRecipe> getRecipeClass() {
		return ExtremeShapedRecipe.class;
	}

	@Override
	public String getRecipeCategoryUid(ExtremeShapedRecipe extremeShapedRecipe) {
		return RecipeCategoryUids.EXTREME_CRAFTING;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(ExtremeShapedRecipe extremeShapedRecipe) {
		return new ExtremeShapedRecipeWrapper(extremeShapedRecipe);
	}

	@Override
	public boolean isRecipeValid(ExtremeShapedRecipe recipe) {
		if (recipe.getRecipeOutput().isEmpty()) {
			String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, getRecipeWrapper(recipe));
			Log.error("Recipe has no outputs. {}", recipeInfo);
			return false;
		}
		int inputCount = 0;
		for (ItemStack input : recipe.recipeItems) {
			if (!input.isEmpty()) {
				inputCount++;
			}
		}
		if (inputCount > 81) {
			String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, getRecipeWrapper(recipe));
			Log.error("Recipe has too many inputs. {}", recipeInfo);
			return false;
		}
		if (inputCount == 0) {
			String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, getRecipeWrapper(recipe));
			Log.error("Recipe has no inputs. {}", recipeInfo);
			return false;
		}
		return true;
	}
}
